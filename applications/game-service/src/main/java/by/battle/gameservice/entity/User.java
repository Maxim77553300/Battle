package by.battle.gameservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    private String id;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Game> games;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}