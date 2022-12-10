package by.battle.userservice.service;

import by.battle.userservice.entity.Role;
import by.battle.userservice.entity.Status;
import by.battle.userservice.entity.User;
import by.battle.userservice.repository.RoleRepository;
import by.battle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_NAME = "USER";
    private static final String ADMIN_NAME = "ADMIN";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return findUserById(id);
    }

    @Override
    public User updateById(User user, String id) {
        User userFromDb = findUserById(id);
        updateUser(user, userFromDb);
        return userRepository.save(userFromDb);
    }

    @Override
    public void delete(String id) {
        User userFromDb = findUserById(id);
        userFromDb.setStatus(Status.NOT_ACTIVE);
        userRepository.save(userFromDb);
    }

    @Override
    public User save(User user) {
        setActiveUser(user);
        return userRepository.save(user);
    }

    private void updateUser(User user, User userFromDb) {
        userFromDb
                .setName(user.getName())
                .setPassword(user.getPassword())
                .setEmail(user.getEmail());
    }

    private void setActiveUser(User user) {
        user
                .setStatus(Status.ACTIVE)
                .setRoles(createSetRole());
    }

    private User findUserById(String id) {
        return userRepository.findById(id).get();
    }

    private Set<Role> createSetRole() {
        return Set.of(roleRepository.findByName(USER_NAME), roleRepository.findByName(ADMIN_NAME));
    }
}
