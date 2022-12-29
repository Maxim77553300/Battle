package by.battle.battleservice.service;

import by.battle.battleservice.entity.User;

public interface UserService {

    User findByName(String name);

    User findById(String id);
}
