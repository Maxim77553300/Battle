package by.battle.gameservice.helpers;

import by.battle.gameservice.dto.UserDto;
import by.battle.gameservice.entity.User;
import by.battle.gameservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserHelper {

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> createListUserDto() {
        return createValidUsers().stream().map(it -> userMapper.mapToDto(it)).collect(Collectors.toList());
    }

    public List<User> createValidUsers() {
        return List.of(createValidUserFirst(), createValidUserSecond());
    }

    public User createValidUserFirst() {
        return new User()
                .setName("Valera1");
    }

    public User createValidUserSecond() {
        return new User()
                .setName("Valera2");
    }
}
