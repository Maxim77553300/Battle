package by.battle.battleservice.service.impl;

import by.battle.battleservice.entity.Game;
import by.battle.battleservice.entity.GameStatus;
import by.battle.battleservice.entity.Move;
import by.battle.battleservice.entity.Result;
import by.battle.battleservice.entity.ResultUser;
import by.battle.battleservice.entity.User;
import by.battle.battleservice.exception.FieldNotFreeException;
import by.battle.battleservice.exception.ItemNotFoundException;
import by.battle.battleservice.exception.NotUserTurnException;
import by.battle.battleservice.repository.GameRepository;
import by.battle.battleservice.service.GameService;
import by.battle.battleservice.service.MoveService;
import by.battle.battleservice.service.ResultService;
import by.battle.battleservice.service.UserService;
import by.battle.battleservice.util.FakeSecurityHolder;
import by.battle.battleservice.util.WinnerCombinationStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ResultService resultService;
    private final MoveService moveService;
    private final FakeSecurityHolder fakeSecurityHolder;
    private final WinnerCombinationStorage winnerCombinationStorage;

    @Override
    public Game create(Game game) {
        game.setName(String.format("Battle %s VS %s", game.getUsers().get(0).getName(), game.getUsers().get(1).getName()));
        game.setStatus(GameStatus.STARTED);
        return gameRepository.save(game);
    }

    @Override
    public Game getById(String id) {
        return gameRepository.findById(id).get();
    }

    @Override
    public Game play(Move move) {
        String userId = fakeSecurityHolder.getTestUserOneId();
        move.setUser(userService.findById(userId));
        Game game = findGame(move);
        isGameHasStatusStarted(game);
        isUserTurn(game, move);
        isFieldNotFree(move, game);
        moveService.save(move);
        return checkWinner(game) ? getFinishedGame(game, move) : saveGame(game);
    }

    private Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    private Game getFinishedGame(Game game, Move move) {
        game.setStatus(GameStatus.FINISHED);
        return gameRepository.save(saveResults(game, move));
    }

    private Game findGame(Move move) {
        return gameRepository.findById(move.getGame().getId())
                .orElseThrow(() -> new ItemNotFoundException(move.getGame().getName()));
    }

    private void isGameHasStatusStarted(Game game) {
        if (game.getStatus().equals(GameStatus.FINISHED)) {
            throw new ItemNotFoundException(game.getName() + " finished");
        }
    }

    private void isFieldNotFree(Move move, Game game) {
        if (moveService.findAllByGameId(game.getId()).stream().anyMatch(it -> it.getField().equals(move.getField()))) {
            throw new FieldNotFreeException(move.getField().getFieldName().name());
        }
    }

    private boolean checkWinner(Game game) {
        return winnerCombinationStorage
                .checkWin(game.getMoves().stream().map(it -> it.getField().getFieldName()).collect(Collectors.toList()));
    }

    private Game saveResults(Game game, Move move) {
        saveResultWin(game, move);
        saveResultLoose(game, move);
        return game;
    }

    private void saveResultLoose(Game game, Move move) {
        User userLoose = game.getUsers().stream()
                .filter(it -> !it.equals(move.getUser())).findFirst().get();
        ResultUser resultUserLoose = new ResultUser()
                .setResult(Result.LOOSE)
                .setUser(userLoose)
                .setGame(game);
        resultService.save(resultUserLoose);
    }

    private void saveResultWin(Game game, Move move) {
        ResultUser resultUserWinner = new ResultUser()
                .setResult(Result.WIN)
                .setUser(move.getUser())
                .setGame(game);
        resultService.save(resultUserWinner);
    }

    private void isUserTurn(Game game, Move move) {
        moveService.findAllByGameId(game.getId()).stream()
                .filter(Objects::nonNull).max(Comparator.comparing(Move::getCreated_at))
                .stream().filter(it -> it.getUser().equals(move.getUser())).findAny().ifPresent(s -> {
                    throw new NotUserTurnException(move.getUser().getId());
                });
    }
}
