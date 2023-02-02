package by.battle.gameservice.util.helper;

import by.battle.gameservice.entity.Cell;
import by.battle.gameservice.entity.Figure;
import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.GameStatus;
import by.battle.gameservice.entity.Move;
import by.battle.gameservice.entity.PlayerFigure;
import by.battle.gameservice.entity.ResultUser;
import by.battle.gameservice.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WinnerCombinationCheckerHelper {

    public List<Move> createValidHorizontalWinMoviesCombination(User user, Integer size) {
        Game validStartedGame = createValidStartedGame(createValidUsers(), size);
        return IntStream.iterate(1, i -> i + 1).limit(size).boxed().collect(Collectors.toList())
                .stream().map(it -> createValidMove(1, it, validStartedGame, user))
                .collect(Collectors.toList());
    }

    public List<Move> createValidVerticalWinMoviesCombination(User user, Integer size) {
        Game validStartedGame = createValidStartedGame(createValidUsers(), size);
        return IntStream.iterate(1, i -> i + 1).limit(size).boxed().collect(Collectors.toList())
                .stream().map(it -> createValidMove(it, 1, validStartedGame, user))
                .collect(Collectors.toList());
    }

    public List<Move> createValidLeftDiagonalWinMoviesCombination(User user, Integer size) {
        Game validStartedGame = createValidStartedGame(createValidUsers(), size);
        return IntStream.iterate(1, i -> i + 1).limit(size).boxed().collect(Collectors.toList())
                .stream().map(it -> createValidMoveForDiagonalWin(it, it, validStartedGame, user))
                .collect(Collectors.toList());
    }

    public List<Move> createValidRightDiagonalWinMoviesCombination(User user, Integer size) {
        Game validStartedGame = createValidStartedGame(createValidUsers(), size);
        List<Move> moves = new ArrayList<>();
        for (int i = size; i > 0; i--) {
            for (int j = 1; j <= size; j++) {
                if (i + j == size + 1) {
                    moves.add(createValidMoveForDiagonalWin(i, j, validStartedGame, user));
                }
            }
        }
        return moves;
    }

    public Move createValidMove(Integer horizontalIndex, Integer verticalIndex, Game game, User user) {
        return (Move) new Move()
                .setGame(game)
                .setCell(new Cell().setGame(game)
                        .setHorizontalIndex(horizontalIndex)
                        .setVerticalIndex(verticalIndex))
                .setUser(user)
                .setCreatedAt(LocalDateTime.now())
                .setId(String.valueOf(horizontalIndex + verticalIndex));
    }

    public Move createValidMoveForDiagonalWin(Integer horizontalIndex, Integer verticalIndex, Game game, User user) {
        return (Move) new Move()
                .setGame(game)
                .setCell(new Cell().setGame(game)
                        .setHorizontalIndex(horizontalIndex)
                        .setVerticalIndex(verticalIndex))
                .setUser(user)
                .setCreatedAt(LocalDateTime.now())
                .setId(String.valueOf(horizontalIndex + verticalIndex));
    }

    public Cell createValidCell(Integer i, Integer j, Game game) {
        return (Cell) new Cell()
                .setHorizontalIndex(i)
                .setVerticalIndex(j)
                .setGame(game)
                .setCreatedAt(LocalDateTime.now())
                .setId(String.valueOf(i + j));
    }

    public Game createValidStartedGame(List<User> users, Integer size) {
        Game game = (Game) new Game()
                .setUsers(users)
                .setStatus(GameStatus.STARTED)
                .setName("Name")
                .setSize(size)
                .setResults(List.of(new ResultUser()))
                .setUpdatedAt(LocalDateTime.now())
                .setPlayerFigures(List.of(new PlayerFigure().setFigure(Figure.X).setUser(users.get(0)),
                        new PlayerFigure().setFigure(Figure.O).setUser(users.get(1))))
                .setCreatedAt(LocalDateTime.now())
                .setId("1");
        return game.setCells(createCells(game));
    }

    public List<Cell> createCells(Game game) {
        int size = game.getSize();
        List<Cell> cells = new ArrayList<>();
        for (int i = size; i > 0; i--) {
            for (int j = 1; j <= size; j++) {
                cells.add(createValidCell(i, j, game));
            }
        }
        return cells;
    }

    public List<User> createValidUsers() {
        return List.of(createValidUserFirst(), createValidUserSecond());
    }

    public User createValidUserFirst() {
        return (User) new User()
                .setName("Valera1")
                .setCreatedAt(LocalDateTime.now())
                .setId("1");
    }

    public User createValidUserSecond() {
        return (User) new User()
                .setName("Valera2")
                .setCreatedAt(LocalDateTime.now())
                .setId("2");
    }
}
