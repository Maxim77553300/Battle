package by.battle.battleservice.service.impl;

import by.battle.battleservice.entity.User;
import by.battle.battleservice.repository.UserRepository;
import by.battle.battleservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }
}
