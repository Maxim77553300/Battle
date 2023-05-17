package by.battle.audit.aspect;

import by.battle.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditGlobalHandler {

    private final AuditLogService auditLogService;

    @Pointcut("execution(* by.battle.*.controller.*Controller.*(..))")
    private void allControllerMethods() {
    }

    @AfterThrowing(pointcut = "execution(* by.battle.*.*.*.*(..))",
            throwing = "exception")
    private void afterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        auditLogService.auditException(joinPoint, exception);
    }

    @Around("allControllerMethods()")
    private Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long begin = System.currentTimeMillis();
        Object targetMethodResult = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();

        auditLogService.audit(proceedingJoinPoint, end - begin);
        return targetMethodResult;
    }
}
