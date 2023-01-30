package by.battle.gameservice.mapper;


import by.battle.gameservice.dto.UserDto;
import by.battle.gameservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto mapToDto(User user);

    User mapFromDto(UserDto userDto);
}
