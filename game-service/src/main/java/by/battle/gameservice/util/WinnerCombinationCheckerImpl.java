package by.battle.gameservice.util;


import by.battle.gameservice.entity.Cell;
import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.Move;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class WinnerCombinationCheckerImpl implements WinnerCombinationChecker {

    @Override
    public boolean checkWin(List<Move> moves) {
        return moves.stream().collect(Collectors.groupingBy(Move::getUser))
                .values().stream().anyMatch(this::checkAllWinCombination);
    }

    private boolean checkAllWinCombination(List<Move> moves) {
        return isFieldPlacesHaveWinHorizontalCombination(moves)
                || isFieldPlacesHaveWinVerticalCombination(moves)
                || isFieldPlacesHaveWinDiagonalCombination(moves);
    }

    private boolean isFieldPlacesHaveWinHorizontalCombination(List<Move> moves) {
        return getIntStream(findGameFomMovies(moves))
                .anyMatch(it -> checkHorizontalLine(moves, it));
    }

    private boolean checkHorizontalLine(List<Move> moves, Integer index) {
        return getFiledPlacesStream(moves)
                .filter(it -> it.getHorizontalIndex().equals(index)).count() == findGameSize(moves);
    }

    private boolean isFieldPlacesHaveWinVerticalCombination(List<Move> moves) {
        return getIntStream(moves.stream().findFirst().get().getGame())
                .anyMatch(it -> checkVerticalLine(moves, it));
    }

    private boolean checkVerticalLine(List<Move> moves, Integer index) {
        return getFiledPlacesStream(moves)
                .filter(it -> it.getVerticalIndex().equals(index)).count() == findGameSize(moves);
    }

    private boolean isFieldPlacesHaveWinDiagonalCombination(List<Move> moves) {
        List<Integer> indexes = getIntStream(findGameFomMovies(moves)).boxed().collect(Collectors.toList());
        return checkDiagonalLineByHorizontalIndex(moves).containsAll(indexes)
                && checkDiagonalLineByVerticalIndex(moves).containsAll(indexes);
    }

    private List<Integer> checkDiagonalLineByHorizontalIndex(List<Move> moves) {
        return getFiledPlacesStream(moves)
                .map(Cell::getHorizontalIndex).collect(Collectors.toList());
    }

    private List<Integer> checkDiagonalLineByVerticalIndex(List<Move> moves) {
        return getFiledPlacesStream(moves)
                .map(Cell::getVerticalIndex).collect(Collectors.toList());
    }

    private Stream<Cell> getFiledPlacesStream(List<Move> moves) {
        return moves.stream().map(Move::getCell);
    }

    private IntStream getIntStream(Game game) {
        return IntStream.iterate(1, i -> i + 1)
                .limit(game.getSize());
    }

    private Integer findGameSize(List<Move> moves) {
        return findGameFomMovies(moves).getSize();
    }

    private Game findGameFomMovies(List<Move> moves) {
        return moves.stream().findFirst().get().getGame();
    }
}
