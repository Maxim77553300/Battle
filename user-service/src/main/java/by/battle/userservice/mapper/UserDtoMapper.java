package by.battle.userservice.mapper;

import by.battle.userservice.dto.UserDto;
import by.battle.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserDtoMapper {

    public abstract UserDto mapToDto(User user);

    public abstract User mapFromDto(UserDto userDto);
}
