package by.battle.userservice.controller;

import by.battle.userservice.dto.UserDto;
import by.battle.userservice.mapper.UserDtoMapper;
import by.battle.userservice.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoMapper userDtoMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> userDtoList = userService.findAll().stream()
                .map(userDtoMapper::mapToDto).collect(Collectors.toList());
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@securityServiceImpl.isManageableUser(#id)")
    public ResponseEntity<UserDto> findById(@PathVariable("id") String id) {
        UserDto userDto = userDtoMapper.mapToDto(userService.findById(id));
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("@securityServiceImpl.isManageableUser(#userDto)")
    public ResponseEntity<UserDto> updateById(@Valid @RequestBody UserDto userDto) {
        UserDto userDtoUpdated = userDtoMapper.mapToDto(userService.update(userDtoMapper.mapFromDto(userDto)));
        return new ResponseEntity<>(userDtoUpdated, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto) {
        UserDto userDtoFromDb = userDtoMapper.mapToDto(userService.create(userDtoMapper.mapFromDto(userDto)));
        return new ResponseEntity<>(userDtoFromDb, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@securityServiceImpl.isManageableUser(#id)")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
