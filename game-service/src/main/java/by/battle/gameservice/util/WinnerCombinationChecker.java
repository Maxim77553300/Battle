package by.battle.gameservice.util;


import by.battle.gameservice.entity.FieldPlace;
import by.battle.gameservice.entity.Move;

import java.util.List;

public interface WinnerCombinationChecker {
    boolean checkWin(List<FieldPlace> fieldPlaces, Move move);
}
