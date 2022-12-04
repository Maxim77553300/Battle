package by.battle.userservice.service;

import by.battle.userservice.dto.UserDto;
import by.battle.userservice.entity.Status;
import by.battle.userservice.entity.User;
import by.battle.userservice.exception.UserNotFoundException;
import by.battle.userservice.mapper.UserDtoMapper;
import by.battle.userservice.repository.RoleRepository;
import by.battle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDtoMapper userDtoMapper;

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userDtoMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(UUID id) {
        return userDtoMapper.mapToDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString())));
    }

    @Override
    public void updateById(UserDto userDto, UUID id) {
        User userFromDb = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));
        userUpdate(userDto, userFromDb);
        userRepository.save(userFromDb);
    }

    @Override
    public void delete(UUID id) {
        User userFromDb = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        userFromDb.setStatus(Status.NOT_ACTIVE);
        userRepository.save(userFromDb);
    }

    @Override
    public void save(UserDto userDto) {
        User user = userDtoMapper
                .mapFromDto(userDto)
                .setStatus(Status.ACTIVE)
                .setRole(Set.of(roleRepository.findByName("USER")));
        userRepository.save(user);
    }

    private void userUpdate(UserDto userDto, User userFromDb) {
        userFromDb
                .setName(userDto.getName())
                .setPassword(userDto.getPassword())
                .setEmail(userDto.getEmail());
    }
}
