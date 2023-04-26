package by.battle.auditservice.service;


import by.battle.auditservice.entity.AuditLog;
import by.battle.auditservice.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public AuditLog save(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    @Override
    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }
}
