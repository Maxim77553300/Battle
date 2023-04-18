package by.battle.gameservice.controller;

import by.battle.gameservice.dto.GameDto;
import by.battle.gameservice.dto.MoveDto;
import by.battle.gameservice.entity.Game;
import by.battle.gameservice.mapper.GameMapper;
import by.battle.gameservice.mapper.MoveMapper;
import by.battle.gameservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final GameMapper gameMapper;
    private final MoveMapper moveMapper;

    @PostMapping
    @PreAuthorize("@securityServiceImpl.isManageableGame(#gameDto)")
    public ResponseEntity<GameDto> createGame(@Valid @RequestBody GameDto gameDto) {
        Game game = gameService.create(gameMapper.mapFromDto(gameDto));
        return new ResponseEntity<>(gameMapper.mapToDto(game), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@securityServiceImpl.isManageableGame(#id)")
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") String id) {
        Game game = gameService.getById(id).get();
        return new ResponseEntity<>(gameMapper.mapToDto(game), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("@securityServiceImpl.isManageableGame(#moveDto)")
    public ResponseEntity<GameDto> play(@Valid @RequestBody MoveDto moveDto) {
        Game game = gameService.play(moveMapper.mapFromDto(moveDto));
        return new ResponseEntity<>(gameMapper.mapToDto(game), HttpStatus.OK);
    }
}
