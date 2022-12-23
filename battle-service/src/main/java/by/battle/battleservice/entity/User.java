package by.battle.battleservice.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String name;
    @CreatedDate
    private LocalDateTime created_at;

    @ManyToMany(mappedBy = "users")
    private List<Game> games;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<ResultUser> resultList;

    public void addResultToList(ResultUser result) {
        resultList.add(result);
    }
}
