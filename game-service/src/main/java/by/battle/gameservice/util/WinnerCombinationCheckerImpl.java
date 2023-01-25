package by.battle.gameservice.util;


import by.battle.gameservice.entity.FieldPlace;
import by.battle.gameservice.entity.Move;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class WinnerCombinationCheckerImpl implements WinnerCombinationChecker {

    public boolean checkWin(List<FieldPlace> fieldPlaces, Move move) {
        return isFieldPlacesHaveWinHorizontalCombination(fieldPlaces, move)
                || isFieldPlacesHaveWinVerticalCombination(fieldPlaces, move)
                || isFieldPlacesHaveWinDiagonalCombination(fieldPlaces, move);
    }

    private boolean isFieldPlacesHaveWinHorizontalCombination(List<FieldPlace> fieldPlaces, Move move) {
        return getIntStream(move)
                .anyMatch(it -> checkHorizontalLine(fieldPlaces, move, it));
    }

    private boolean checkHorizontalLine(List<FieldPlace> fieldPlaces, Move move, Integer index) {
        return getFiledPlacesStream(fieldPlaces, move)
                .filter(it -> it.getHorizontalIndex().equals(index)).count() == move.getGame().getSize();
    }

    private boolean isFieldPlacesHaveWinVerticalCombination(List<FieldPlace> fieldPlaces, Move move) {
        return getIntStream(move)
                .anyMatch(it -> checkVerticalLine(fieldPlaces, move, it));
    }

    private boolean checkVerticalLine(List<FieldPlace> fieldPlaces, Move move, Integer index) {
        return getFiledPlacesStream(fieldPlaces, move)
                .filter(it -> it.getVerticalIndex().equals(index)).count() == move.getGame().getSize();
    }

    private boolean isFieldPlacesHaveWinDiagonalCombination(List<FieldPlace> fieldPlaces, Move move) {
        List<Integer> indexes = getIntStream(move).boxed().collect(Collectors.toList());
        return checkDiagonalLineByHorizontalIndex(fieldPlaces, move).containsAll(indexes)
                || checkDiagonalLineByVerticalIndex(fieldPlaces, move).containsAll(indexes);
    }

    private List<Integer> checkDiagonalLineByHorizontalIndex(List<FieldPlace> fieldPlaces, Move move) {
        return getFiledPlacesStream(fieldPlaces, move)
                .map(it -> it.getMove().getFieldPlace().getHorizontalIndex()).collect(Collectors.toList());
    }

    private List<Integer> checkDiagonalLineByVerticalIndex(List<FieldPlace> fieldPlaces, Move move) {
        return getFiledPlacesStream(fieldPlaces, move)
                .map(it -> it.getMove().getFieldPlace().getVerticalIndex()).collect(Collectors.toList());
    }

    private Stream<FieldPlace> getFiledPlacesStream(List<FieldPlace> fieldPlaces, Move move) {
        return fieldPlaces.stream().filter(it -> it.getMove().getUser().getId().equals(move.getUser().getId()));
    }

    private IntStream getIntStream(Move move) {
        return IntStream.iterate(1, i -> i + 1)
                .limit(move.getGame().getSize());
    }
}
