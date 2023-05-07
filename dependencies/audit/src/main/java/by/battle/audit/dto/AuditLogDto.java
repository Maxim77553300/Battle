package by.battle.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogDto implements Serializable {

    private String id;
    private String action;
    private String methodSignature;
    private String detail;
    private LocalDateTime createdAt;
    private String entityId;
    private String processingTime;
}
