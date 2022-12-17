package by.battle.battleservice.util;

import by.battle.battleservice.entity.Field;

import java.util.List;

public interface WinnerCombinationStorage {
    boolean checkWin(List<Field> fieldList);
}
