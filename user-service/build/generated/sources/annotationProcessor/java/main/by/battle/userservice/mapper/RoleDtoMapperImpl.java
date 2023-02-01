package by.battle.userservice.mapper;

import by.battle.userservice.dto.RoleDto;
import by.battle.userservice.entity.Role;
import by.battle.userservice.entity.RoleName;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-19T15:34:30+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class RoleDtoMapperImpl implements RoleDtoMapper {

    @Override
    public RoleDto mapToDto(Role roles) {
        if ( roles == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( roles.getId() );
        if ( roles.getName() != null ) {
            roleDto.setName( roles.getName().name() );
        }

        return roleDto;
    }

    @Override
    public Role mapFromDto(RoleDto roleDto) {
        if ( roleDto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDto.getId() );
        if ( roleDto.getName() != null ) {
            role.setName( Enum.valueOf( RoleName.class, roleDto.getName() ) );
        }

        return role;
    }
}
