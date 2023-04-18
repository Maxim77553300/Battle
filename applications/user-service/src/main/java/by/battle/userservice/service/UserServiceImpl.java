package by.battle.userservice.service;

import by.battle.common.RoleName;
import by.battle.userservice.entity.Role;
import by.battle.userservice.entity.Status;
import by.battle.userservice.entity.User;
import by.battle.userservice.exception.UserNotFoundException;
import by.battle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return findUserById(id);
    }

    @Override
    public User update(User user) {
        User userFromDb = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getId()));
        return userRepository.save(updateUserFields(user, userFromDb));
    }

    @Override
    public void delete(String id) {
        User userFromDb = findUserById(id);
        userFromDb.setStatus(Status.NOT_ACTIVE);
        userRepository.save(userFromDb);
    }

    @Override
    @Transactional
    public User create(User user) {
        user
                .setPassword(passwordEncoder.encode(user.getPassword()))
                .setStatus(Status.ACTIVE)
                .setRoles(createListRole());
        return userRepository.save(user);
    }

    private User updateUserFields(User user, User userFromDb) {
        return user
                .setName(userFromDb.getName())
                .setStatus(userFromDb.getStatus())
                .setRoles(userFromDb.getRoles())
                .setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private User findUserById(String id) {
        return userRepository.findById(id).get();
    }

    private List<Role> createListRole() {
        return List.of(roleService.findByName(RoleName.CUSTOMER));
    }
}
