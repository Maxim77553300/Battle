package by.battle.gameservice.mapper;


import by.battle.gameservice.dto.GameDto;
import by.battle.gameservice.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {
        MoveMapper.class,
        ResultUserMapper.class,
        PlayerFigureMapper.class,
        UserMapper.class})
public interface GameMapper {

    GameDto mapToDto(Game game);

    Game mapFromDto(GameDto gameDto);
}
