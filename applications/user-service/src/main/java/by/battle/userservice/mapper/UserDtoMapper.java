package by.battle.userservice.mapper;

import by.battle.userservice.dto.UserDto;
import by.battle.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto mapToDto(User user);

    User mapFromDto(UserDto userDto);
}
