package by.battle.userservice.mapper;

import by.battle.userservice.dto.RoleDto;
import by.battle.userservice.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {

    RoleDto mapToDto(Role roles);

    Role mapFromDto(RoleDto roleDto);
}
