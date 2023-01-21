package by.battle.gameservice.mapper;


import by.battle.gameservice.dto.GameDto;
import by.battle.gameservice.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MoveDtoMapper.class, ResultUserDtoMapper.class})
public interface GameDtoMapper {

    GameDto mapToDto(Game game);

    Game mapFromDto(GameDto gameDto);
}
