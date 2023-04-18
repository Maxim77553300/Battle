package by.battle.userservice.service;

import by.battle.common.RoleName;
import by.battle.userservice.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(RoleName name);

    List<Role> findAll();
}
