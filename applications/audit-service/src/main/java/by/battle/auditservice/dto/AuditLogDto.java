package by.battle.auditservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogDto {

    private String id;
    private String action;
    private String methodSignature;
    private String detail;
    private LocalDateTime createdAt;
    private String entityId;
    private String processingTime;
}
