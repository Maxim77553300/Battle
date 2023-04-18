package by.battle.userservice.service;

import by.battle.userservice.dto.UserDto;

public interface SecurityService {

    boolean isManageableUser(UserDto userDto);

    boolean isManageableUser(String userId);

    boolean isUserAdmin();
}
