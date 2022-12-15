package by.battle.userservice.service;

import by.battle.userservice.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String id);

    User updateById(User user);

    void delete(String id);

    User create(User user);
}
