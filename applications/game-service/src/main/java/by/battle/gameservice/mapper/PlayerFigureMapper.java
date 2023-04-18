package by.battle.gameservice.mapper;


import by.battle.gameservice.dto.PlayerFigureDto;
import by.battle.gameservice.entity.PlayerFigure;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerFigureMapper {

    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "user.id", target = "userId")
    PlayerFigureDto mapToDto(PlayerFigure playerFigureUser);

    PlayerFigure mapFromDto(PlayerFigureDto playerFigureUserDto);
}
