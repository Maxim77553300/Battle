package by.battle.auditservice.listener;

import by.battle.auditservice.dto.AuditLogDto;
import by.battle.auditservice.mapper.AuditLogMapper;
import by.battle.auditservice.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaListener {

    private final AuditService auditService;

    private final AuditLogMapper auditLogMapper;

    @org.springframework.kafka.annotation.KafkaListener(topics = "demo_topic", groupId = "myGroup")
    void listener(AuditLogDto auditLogDto) {
        auditService.save(auditLogMapper.mapFromDto(auditLogDto));
    }
}
