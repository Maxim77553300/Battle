package by.battle.battleservice.mapper;

import by.battle.battleservice.dto.UserDto;
import by.battle.battleservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto mapToDto(User user);

    User mapFromDto(UserDto userDto);
}
