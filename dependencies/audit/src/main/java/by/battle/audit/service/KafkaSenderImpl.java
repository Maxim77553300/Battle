package by.battle.audit.service;

import by.battle.audit.dto.AuditLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSenderImpl implements KafkaSender{

    private final KafkaTemplate<String ,AuditLogDto> kafkaTemplate;

    @Override
    public void sendMessage(AuditLogDto auditLogDto, String topicName) {
        kafkaTemplate.send(topicName, auditLogDto);
    }
}
