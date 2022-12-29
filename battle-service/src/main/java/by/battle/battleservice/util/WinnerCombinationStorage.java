package by.battle.battleservice.util;

import by.battle.battleservice.entity.FieldName;

import java.util.List;

public interface WinnerCombinationStorage {
    boolean checkWin(List<FieldName> fieldNameList);
}
