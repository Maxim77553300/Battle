package by.battle.userservice.service;

import by.battle.userservice.entity.Role;
import by.battle.userservice.entity.Status;
import by.battle.userservice.entity.User;
import by.battle.userservice.exception.UserNotFoundException;
import by.battle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER_NAME = "USER";
    private static final String ADMIN_NAME = "ADMIN";

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return findUserById(id);
    }

    @Override
    public User updateById(User user) {
        User userFromDb = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
        user.setStatus(userFromDb.getStatus());
        user.setRoles(userFromDb.getRoles());
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        User userFromDb = findUserById(id);
        userFromDb.setStatus(Status.NOT_ACTIVE);
        userRepository.save(userFromDb);
    }

    @Override
    public User create(User user) {
        user
                .setStatus(Status.ACTIVE)
                .setRoles(createListRole());
        return userRepository.save(user);
    }

    private User findUserById(String id) {
        return userRepository.findById(id).get();
    }

    private List<Role> createListRole() {
        return List.of(roleService.findByName(USER_NAME), roleService.findByName(ADMIN_NAME));
    }
}
