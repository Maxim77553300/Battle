package by.battle.gameservice.util;

import by.battle.gameservice.entity.Move;
import by.battle.gameservice.entity.User;
import by.battle.gameservice.util.helper.WinnerCombinationCheckerHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WinnerCombinationIntegrationTest {

    private final static Integer size = 3;
    private final static Integer index = 0;

    private WinnerCombinationChecker winnerCombinationChecker;
    private WinnerCombinationCheckerHelper winnerCombinationCheckerHelper;

    @BeforeEach
    void setUp() {
        winnerCombinationChecker = new WinnerCombinationCheckerImpl();
        winnerCombinationCheckerHelper = new WinnerCombinationCheckerHelper();
    }

    @Test
    public void checkWin_shouldReturnTrue_whenValidMoviesHaveHorizontalWin() {
        final User user = winnerCombinationCheckerHelper.createValidUserFirst();

        final boolean actualResult = winnerCombinationChecker
                .checkWin(winnerCombinationCheckerHelper
                        .createValidHorizontalWinMoviesCombination(user, size));

        assertTrue(actualResult);
    }

    @Test
    public void checkWin_shouldReturnTrue_whenValidMoviesHaveVerticalWin() {
        final User user = winnerCombinationCheckerHelper.createValidUserFirst();

        final boolean actualResult = winnerCombinationChecker
                .checkWin(winnerCombinationCheckerHelper
                        .createValidVerticalWinMoviesCombination(user, size));

        assertTrue(actualResult);
    }

    @Test
    public void checkWin_shouldReturnTrue_whenValidMoviesHaveLeftDiagonalWin() {
        final User user = winnerCombinationCheckerHelper.createValidUserFirst();

        final boolean actualResult = winnerCombinationChecker
                .checkWin(winnerCombinationCheckerHelper
                        .createValidLeftDiagonalWinMoviesCombination(user, size));

        assertTrue(actualResult);
    }

    @Test
    public void checkWin_shouldReturnTrue_whenValidMoviesHaveRightDiagonalWin() {
        final User user = winnerCombinationCheckerHelper.createValidUserFirst();

        final boolean actualResult = winnerCombinationChecker
                .checkWin(winnerCombinationCheckerHelper
                        .createValidRightDiagonalWinMoviesCombination(user, size));

        assertTrue(actualResult);
    }

    @Test
    public void checkWin_shouldReturnFalse_whenValidMoviesNotHaveHorizontalWin() {
        final User user = winnerCombinationCheckerHelper.createValidUserFirst();
        final List<Move> moves = winnerCombinationCheckerHelper.createValidHorizontalWinMoviesCombination(user, size);

        moves.set(index, winnerCombinationCheckerHelper
                .createValidMove(size - 1, size - 1, moves.get(index).getGame(), user));

        final boolean actualResult = winnerCombinationChecker
                .checkWin(moves);

        assertFalse(actualResult);
    }

    @Test
    public void checkWin_shouldReturnFalse_whenValidMoviesHaveNotVerticalWin() {
        final User user = winnerCombinationCheckerHelper.createValidUserFirst();
        final List<Move> moves = winnerCombinationCheckerHelper.createValidVerticalWinMoviesCombination(user, size);

        moves.set(index, winnerCombinationCheckerHelper
                .createValidMove(1, size - 1, moves.get(index).getGame(), user));

        final boolean actualResult = winnerCombinationChecker
                .checkWin(moves);

        assertFalse(actualResult);
    }

    @Test
    public void checkWin_shouldReturnFalse_whenValidMoviesHaveNotLeftDiagonalWin() {
        final User user = winnerCombinationCheckerHelper.createValidUserFirst();
        final List<Move> moves = winnerCombinationCheckerHelper
                .createValidLeftDiagonalWinMoviesCombination(user, size);

        moves.set(index, winnerCombinationCheckerHelper
                .createValidMove(1, size, moves.get(index).getGame(), user));

        final boolean actualResult = winnerCombinationChecker.checkWin(moves);

        assertFalse(actualResult);
    }

    @Test
    public void checkWin_shouldReturnFalse_whenValidMoviesHaveNotRightDiagonalWin() {
        final User user = winnerCombinationCheckerHelper.createValidUserFirst();
        final List<Move> moves = winnerCombinationCheckerHelper
                .createValidRightDiagonalWinMoviesCombination(user, size);

        moves.set(index, winnerCombinationCheckerHelper
                .createValidMove(size, size, moves.get(index).getGame(), user));

        final boolean actualResult = winnerCombinationChecker.checkWin(moves);

        assertFalse(actualResult);
    }
}