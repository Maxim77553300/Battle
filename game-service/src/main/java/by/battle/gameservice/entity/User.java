package by.battle.gameservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Game> games;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
