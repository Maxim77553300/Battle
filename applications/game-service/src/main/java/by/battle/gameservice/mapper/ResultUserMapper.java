package by.battle.gameservice.mapper;


import by.battle.gameservice.dto.ResultUserDto;
import by.battle.gameservice.entity.ResultUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResultUserMapper {

    @Mapping(source = "game.id", target = "gameId")
    @Mapping(source = "user.id", target = "userId")
    ResultUserDto mapToDto(ResultUser resultUser);

    ResultUser mapFromDto(ResultUserDto resultUserDto);
}
