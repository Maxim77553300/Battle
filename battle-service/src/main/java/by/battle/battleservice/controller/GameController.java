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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;
    private final GameDtoMapper gameDtoMapper;
    private final MoveDtoMapper moveDtoMapper;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody GameDto gameDto) {
        Game game = gameService.create(gameDtoMapper.mapFromDto(gameDto));
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(GameDto gameDto) {
        Game game = gameService.getById(gameDtoMapper.mapFromDto(gameDto));
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Game> play(@RequestBody MoveDto moveDto) {
//        Field playerOneChoice = Optional.of(Field.valueOf(playerOneChoice.getChoice()))
//                .orElseThrow(() -> new ItemNotFoundException("There is no this choice"));
//        Field playerTwoChoice = Field.getRandom();
        return new ResponseEntity<>(gameService.play(moveDtoMapper.mapFromDto(moveDto)), HttpStatus.OK);
    }
}
