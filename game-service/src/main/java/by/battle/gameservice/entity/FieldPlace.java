package by.battle.gameservice.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Setter
@Getter
@Entity
public class FieldPlace extends BaseEntity {

    private Integer horizontalIndex;
    private Integer verticalIndex;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @OneToOne
    @JoinColumn(name = "move_id", referencedColumnName = "id")
    private Move move;
}
