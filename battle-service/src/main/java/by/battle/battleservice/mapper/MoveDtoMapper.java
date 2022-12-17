package by.battle.battleservice.mapper;

import by.battle.battleservice.dto.MoveDto;
import by.battle.battleservice.dto.UserDto;
import by.battle.battleservice.entity.Move;
import by.battle.battleservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoveDtoMapper {

    MoveDto mapToDto(Move move);

    Move mapFromDto(MoveDto moveDto);
}
