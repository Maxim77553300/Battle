package by.battle.gameservice.mapper;


import by.battle.gameservice.dto.ResultUserDto;
import by.battle.gameservice.entity.ResultUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class ResultUserMapper {

    public abstract ResultUserDto mapToDto(ResultUser resultUser);

    public abstract ResultUser mapFromDto(ResultUserDto resultUserDto);

    @AfterMapping
    public void after(@MappingTarget ResultUserDto resultUserDto, ResultUser resultUser) {
        resultUserDto.setUserId(resultUser.getUser().getId());
        resultUserDto.setGameId(resultUser.getGame().getId());
    }
}
