package by.battle.gameservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ToString
@Setter
@Getter
@Entity
public class Cell extends BaseEntity {

    private Integer horizontalIndex;
    private Integer verticalIndex;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;
}
