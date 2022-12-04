package by.battle.userservice.service;

import by.battle.userservice.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDto> findAll();

    UserDto findById(UUID id);

    void updateById(UserDto userDto, UUID id);

    void delete(UUID id);

    void save(UserDto userDto);
}
