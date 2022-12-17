package by.battle.battleservice.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Entity
public class Game {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_status")
    private GameStatus gameStatus;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private User player1;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private User player2;

    @Column(name = "winner_name")
    private String winnerName;
}
