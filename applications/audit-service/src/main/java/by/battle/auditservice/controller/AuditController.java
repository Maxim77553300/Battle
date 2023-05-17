package by.battle.auditservice.controller;


import by.battle.auditservice.dto.AuditLogDto;
import by.battle.auditservice.entity.AuditLog;
import by.battle.auditservice.mapper.AuditLogMapper;
import by.battle.auditservice.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/audit-service")
@RequiredArgsConstructor
public class AuditController {

    private final AuditLogMapper auditLogMapper;

    private final AuditService auditService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AuditLogDto saveAuditLog(@RequestBody AuditLogDto auditLogDto) {
        AuditLog auditLogToSave = auditLogMapper.mapFromDto(auditLogDto);
        return auditLogMapper.mapToDto(auditService.save(auditLogToSave));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuditLogDto> findAllAuditLogs() {
        return auditService.findAll().stream()
                .map(auditLogMapper::mapToDto).collect(Collectors.toList());
    }
}
