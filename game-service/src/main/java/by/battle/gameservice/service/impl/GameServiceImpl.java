package by.battle.gameservice.service.impl;


import by.battle.gameservice.entity.Cell;
import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.GameStatus;
import by.battle.gameservice.entity.Move;
import by.battle.gameservice.entity.PlayerFigure;
import by.battle.gameservice.entity.Result;
import by.battle.gameservice.entity.ResultUser;
import by.battle.gameservice.entity.User;
import by.battle.gameservice.exception.FieldNotFreeException;
import by.battle.gameservice.exception.ItemNotFoundException;
import by.battle.gameservice.exception.NotUserTurnException;
import by.battle.gameservice.repository.GameRepository;
import by.battle.gameservice.service.GameService;
import by.battle.gameservice.service.MoveService;
import by.battle.gameservice.service.ResultService;
import by.battle.gameservice.service.UserService;
import by.battle.gameservice.util.FakeSecurityHolder;
import by.battle.gameservice.util.WinnerCombinationChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ResultService resultService;
    private final MoveService moveService;
    private final FakeSecurityHolder fakeSecurityHolder;
    private final WinnerCombinationChecker winnerCombinationChecker;

    @Override
    public Game create(Game game) {
        game.setName(String.format("Battle %s VS %s", game.getUsers().get(0).getName(), game.getUsers().get(1).getName()));
        game.setStatus(GameStatus.STARTED);
        game.setCells(generateFieldFromSize(game));
        game.setUsers(getUsersFromDbIfExist(game.getUsers()));
        setResultInProgress(game);
        game.getUsers().forEach(userService::save);
        game.setPlayerFigures(createPlayerFigures(game));
        return gameRepository.save(game);
    }

    private List<PlayerFigure> createPlayerFigures(Game game) {
        return game.getPlayerFigures().stream()
                .peek(it -> it.setGame(game))
                .map(it -> it.setUser(userService.findByName(it.getUserName())
                        .orElseGet(it::getUser)))
                .collect(Collectors.toList());
    }

    private List<User> getUsersFromDbIfExist(List<User> users) {
        return users.stream().map(it -> userService
                .findByName(it.getName()).orElseGet(() -> it)).collect(Collectors.toList());
    }

    private List<Cell> generateFieldFromSize(Game game) {
        int size = game.getSize();
        List<Cell> cells = new ArrayList<>();
        for (int i = size; i > 0; i--) {
            for (int j = 1; j <= size; j++) {
                cells.add(new Cell().setHorizontalIndex(i).setVerticalIndex(j).setGame(game));
            }
        }
        return cells;
    }

    @Override
    public Optional<Game> getById(String id) {
        return gameRepository.findById(id);
    }

    @Override
    public Game play(Move move) {
        String userId = fakeSecurityHolder.getTestUserOneId();
        move.setUser(userService.findById(userId));
        Game game = findGame(move);
        isGameHasNotStatusFinished(game);
        isUserTurn(game, move);
        isFieldNotFree(move, game);
        moveService.save(move);
        game.setStatus(GameStatus.WAITING_FOR_OPPONENT);
        return checkWinner(game, move) ? getFinishedGame(game, move) : saveGame(game);
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

    private void isGameHasNotStatusFinished(Game game) {
        if (game.getStatus().equals(GameStatus.FINISHED)) {
            throw new ItemNotFoundException(game.getName() + " finished");
        }
    }

    private void isFieldNotFree(Move move, Game game) {
        if (moveService.findAllByGameId(game.getId()).stream()
                .anyMatch(it -> it.getCell().equals(move.getCell()))) {
            throw new FieldNotFreeException(move.getCell().toString());
        }
    }

    private boolean checkWinner(Game game, Move move) {
        return winnerCombinationChecker
                .checkWin(game.getMoves());
    }

    private Game saveResults(Game game, Move move) {
        saveResultWin(game, move);
        saveResultLoose(game, move);
        return game;
    }

    private void saveResultLoose(Game game, Move move) {
        User userLoose = game.getUsers().stream()
                .filter(it -> !it.equals(move.getUser())).findFirst().get();
        ResultUser resultUser = resultService.findByUserAndGame(userLoose, game).get().setResult(Result.LOOSE);
        resultService.save(resultUser);
    }

    private void saveResultWin(Game game, Move move) {
        ResultUser resultUser = resultService.findByUserAndGame(move.getUser(), game).get().setResult(Result.WIN);
        resultService.save(resultUser);
    }

    private void setResultInProgress(Game game) {
        game.setResults(List.of(createResultUser(game, game.getUsers().get(0), Result.IN_PROGRESS),
                createResultUser(game, game.getUsers().get(1), Result.IN_PROGRESS)));
    }

    private ResultUser createResultUser(Game game, User user, Result result) {
        return new ResultUser()
                .setResult(result)
                .setUser(user)
                .setGame(game);
    }

    private void isUserTurn(Game game, Move move) {
        moveService.findAllByGameId(game.getId()).stream()
                .filter(Objects::nonNull).max(Comparator.comparing(Move::getCreatedAt))
                .stream().filter(it -> it.getUser().equals(move.getUser())).findAny().ifPresent(s -> {
                    throw new NotUserTurnException(move.getUser().getId());
                });
    }
}
