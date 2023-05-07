package by.battle.audit.service;

import by.battle.audit.dto.AuditLogDto;

public interface KafkaSender {

    void sendMessage(AuditLogDto auditLogDto, String topicName);
}
