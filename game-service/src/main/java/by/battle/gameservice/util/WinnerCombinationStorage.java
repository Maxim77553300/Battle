package by.battle.gameservice.util;


import by.battle.gameservice.entity.FieldName;

import java.util.List;

public interface WinnerCombinationStorage {
    boolean checkWin(List<FieldName> fieldNameList);
}
