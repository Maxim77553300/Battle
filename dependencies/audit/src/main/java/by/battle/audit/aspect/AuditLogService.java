package by.battle.audit.aspect;


import by.battle.audit.aspect.restuservlient.RestUserClient;
import by.battle.security.util.AuthUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final RestUserClient restUserClient;

    public void audit(ProceedingJoinPoint proceedingJoinPoint, long time) {
        AuditLogDto auditLogDto = createAuditLogDto(proceedingJoinPoint, time);
        restUserClient.sendAuditLog(auditLogDto);
    }

    public void auditException(JoinPoint joinPoint, Throwable exception) {
        AuditLogDto auditLogDto = mapToAuditLogDto((MethodSignature) joinPoint.getSignature(),
                joinPoint.getArgs(), exception);
        restUserClient.sendAuditLog(auditLogDto);
    }

    private String getCurrentUserId() {
        return isCurrentTokenNull() ? "Unregistered user" : AuthUtility.getCurrentUserId();
    }

    private boolean isCurrentTokenNull() {
        return AuthUtility.getCurrentToken() == null;
    }

    private AuditLogDto createAuditLogDto(ProceedingJoinPoint proceedingJoinPoint, long time) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object[] args = proceedingJoinPoint.getArgs();
        return mapToAuditLogDto(methodSignature, args, time);
    }

    private AuditLogDto mapToAuditLogDto(MethodSignature methodSignature, Object[] args, long time) {
        return mapToAuditLogDto(methodSignature, args)
                .setProcessingTime(String.valueOf(time))
                .setAction(methodSignature.getMethod().getName());
    }

    private AuditLogDto mapToAuditLogDto(MethodSignature methodSignature, Object[] args, Throwable exception) {
        return mapToAuditLogDto(methodSignature, args)
                .setProcessingTime(null)
                .setAction(exception.getMessage());
    }

    private AuditLogDto mapToAuditLogDto(MethodSignature methodSignature, Object[] args) {
        return new AuditLogDto()
                .setMethodSignature(methodSignature.toString())
                .setEntityId(getCurrentUserId())
                .setDetail(Arrays.toString(args));
    }
}
