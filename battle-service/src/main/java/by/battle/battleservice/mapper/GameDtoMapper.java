package by.battle.battleservice.mapper;

import by.battle.battleservice.dto.GameDto;
import by.battle.battleservice.entity.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MoveDtoMapper.class, ResultUserDtoMapper.class})
public interface GameDtoMapper {

    GameDto mapToDto(Game game);

    Game mapFromDto(GameDto gameDto);
}
