package by.battle.userservice.mapper;

import by.battle.userservice.dto.RoleDto;
import by.battle.userservice.entity.Role;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {

    RoleDto mapToDto(Role roles);

    @InheritInverseConfiguration
    Role mapFromDto(RoleDto roleDto);
}
