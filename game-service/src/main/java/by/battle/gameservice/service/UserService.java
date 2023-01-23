package by.battle.gameservice.service;


import by.battle.gameservice.entity.User;

public interface UserService {

    User findByName(String name);

    User findById(String id);
}
