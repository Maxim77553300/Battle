package by.battle.userservice.service;

import by.battle.userservice.entity.Role;
import by.battle.userservice.entity.RoleName;

import java.util.List;

public interface RoleService {
    Role findByName(RoleName name);

    List<Role> findAll();
}
