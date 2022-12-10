package by.battle.userservice.mapper;

import by.battle.userservice.dto.UserDto;
import by.battle.userservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = RoleDtoMapper.class)
public interface UserDtoMapper {

    @Mapping(source = "roles", target = "roleSet")
    UserDto mapToDto(User user);

    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(source = "roleSet", target = "roles")
    User mapFromDto(UserDto userDto);
}
