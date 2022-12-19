package by.battle.userservice.service;

import by.battle.userservice.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);

    List<Role> findAll();
}
