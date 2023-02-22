package by.battle.gameservice.helpers;

import by.battle.gameservice.dto.GameDto;
import by.battle.gameservice.dto.PlayerFigureDto;
import by.battle.gameservice.entity.Figure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameHelper {

    @Autowired
    private UserHelper userHelper;

    public GameDto createValidGameDto() {
        return new GameDto()
                .setUsers(userHelper.createListUserDto())
                .setSize(4)
                .setPlayerFigures(createPlayerFigureList());
    }

    public List<PlayerFigureDto> createPlayerFigureList() {
        return List.of(new PlayerFigureDto().setFigure(Figure.X).setUserName(userHelper.createValidUserFirst().getName()),
                new PlayerFigureDto().setFigure(Figure.O).setUserName(userHelper.createValidUserSecond().getName()));
    }

    public List<PlayerFigureDto> createPlayerFigureDtoListWithTheSameFigure() {
        return List.of(new PlayerFigureDto().setFigure(Figure.X).setUserName(userHelper.createValidUserFirst().getName()),
                new PlayerFigureDto().setFigure(Figure.X).setUserName(userHelper.createValidUserSecond().getName()));
    }

    public List<PlayerFigureDto> createPlayerFigureListWithTheSameNames() {
        return List.of(new PlayerFigureDto().setFigure(Figure.X).setUserName(userHelper.createValidUserFirst().getName()),
                new PlayerFigureDto().setFigure(Figure.O).setUserName(userHelper.createValidUserFirst().getName()));
    }
}
