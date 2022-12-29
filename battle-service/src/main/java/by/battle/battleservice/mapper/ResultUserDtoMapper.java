package by.battle.battleservice.mapper;

import by.battle.battleservice.dto.ResultUserDto;
import by.battle.battleservice.entity.ResultUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ResultUserDtoMapper {

    public abstract ResultUserDto mapToDto(ResultUser resultUser);

    public abstract ResultUser mapFromDto(ResultUserDto resultUserDto);

    @AfterMapping
    public void after(@MappingTarget ResultUserDto resultUserDto, ResultUser resultUser) {
        resultUserDto.setUserId(resultUser.getUser().getId());
        resultUserDto.setGameId(resultUser.getGame().getId());
    }
}
