package by.battle.battleservice.controller;

import by.battle.battleservice.dto.GameDto;
import by.battle.battleservice.dto.MoveDto;
import by.battle.battleservice.entity.Game;
import by.battle.battleservice.mapper.GameDtoMapper;
import by.battle.battleservice.mapper.MoveDtoMapper;
import by.battle.battleservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final GameDtoMapper gameDtoMapper;
    private final MoveDtoMapper moveDtoMapper;

    @PostMapping
    public ResponseEntity<GameDto> createGame(@Valid @RequestBody GameDto gameDto) {
        System.out.println(gameDto);
        Game game = gameService.create(gameDtoMapper.mapFromDto(gameDto));
        return new ResponseEntity<>(gameDtoMapper.mapToDto(game), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") String id) {
        GameDto gameDto = gameDtoMapper.mapToDto(gameService.getById(id));
        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<GameDto> play(@RequestBody MoveDto moveDto) {
        GameDto gameDto = gameDtoMapper.mapToDto(gameService.play(moveDtoMapper.mapFromDto(moveDto)));
        return new ResponseEntity<>(gameDto, HttpStatus.OK);
    }
}
