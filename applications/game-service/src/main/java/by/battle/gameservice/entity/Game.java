package by.battle.gameservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Game extends BaseEntity {

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private String name;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private Integer size;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<Move> moves;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<ResultUser> results;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<Cell> cells;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<PlayerFigure> playerFigures;
}
