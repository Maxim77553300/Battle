package by.battle.gameservice.service;


import by.battle.gameservice.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByName(String name);

    User findById(String id);

    User save(User user);
}
