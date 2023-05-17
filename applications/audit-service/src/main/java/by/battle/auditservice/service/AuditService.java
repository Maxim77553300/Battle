package by.battle.auditservice.service;


import by.battle.auditservice.entity.AuditLog;

import java.util.List;

public interface AuditService {

    AuditLog save(AuditLog auditLog);

    List<AuditLog> findAll();
}
