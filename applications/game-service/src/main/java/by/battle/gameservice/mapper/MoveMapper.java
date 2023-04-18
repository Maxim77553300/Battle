package by.battle.gameservice.mapper;

import by.battle.gameservice.dto.MoveDto;
import by.battle.gameservice.entity.Move;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MoveMapper {

    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "user.id", target = "userId")
    MoveDto mapToDto(Move move);

    @Mapping(source = "gameId", target = "game.id")
    Move mapFromDto(MoveDto moveDto);
}
