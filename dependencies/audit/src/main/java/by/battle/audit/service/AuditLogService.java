package by.battle.audit.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public interface AuditLogService {

    void audit(ProceedingJoinPoint proceedingJoinPoint, long time);

    void auditException(JoinPoint joinPoint, Throwable exception);
}
