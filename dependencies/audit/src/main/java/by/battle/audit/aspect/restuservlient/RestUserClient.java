package by.battle.audit.aspect.restuservlient;


import by.battle.audit.aspect.AuditLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestUserClient {

    private final RestTemplate restTemplate;

    @Value("${audit-service.url}")
    private String auditServiceUrl;


    public AuditLogDto sendAuditLog(AuditLogDto auditLogDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuditLogDto> httpEntity = new HttpEntity<>(auditLogDto, headers);
        return restTemplate.postForObject(auditServiceUrl, httpEntity, AuditLogDto.class);
    }

}
