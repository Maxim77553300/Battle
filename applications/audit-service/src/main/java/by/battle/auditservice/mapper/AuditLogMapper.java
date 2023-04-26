package by.battle.auditservice.mapper;


import by.battle.auditservice.dto.AuditLogDto;
import by.battle.auditservice.entity.AuditLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {

    AuditLogDto mapToDto(AuditLog auditLog);

    AuditLog mapFromDto(AuditLogDto auditLogDto);
}
