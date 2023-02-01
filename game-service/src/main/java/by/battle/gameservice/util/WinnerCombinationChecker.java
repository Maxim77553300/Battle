package by.battle.gameservice.util;


import by.battle.gameservice.entity.Move;

import java.util.List;

public interface WinnerCombinationChecker {
    boolean checkWin(List<Move> moves);
}
