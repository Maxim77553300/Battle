package by.battle.userservice.service.impl;

import by.battle.common.RoleName;
import by.battle.security.util.AuthUtility;
import by.battle.userservice.dto.UserDto;
import by.battle.userservice.service.SecurityService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Override
    public boolean isManageableUser(UserDto userDto) {
        return isManageableUser(userDto.getId());
    }

    @Override
    public boolean isManageableUser(String id) {
        return isUserAdmin() || isUser(id);
    }

    public boolean isUserAdmin() {
        return AuthUtility.getCurrentRoleNames().contains(RoleName.ADMIN);
    }

    private boolean isUser(String userId) {
        return AuthUtility.getCurrentUserId().equals(userId);
    }
}
