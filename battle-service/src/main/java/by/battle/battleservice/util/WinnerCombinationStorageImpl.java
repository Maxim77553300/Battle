package by.battle.battleservice.util;

import by.battle.battleservice.entity.FieldName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WinnerCombinationStorageImpl implements WinnerCombinationStorage {

    private final Map<String, List<FieldName>> mapListsWinnerChoices;

    public boolean checkWin(List<FieldName> fieldNameList) {
        return mapListsWinnerChoices.values().stream().anyMatch(entry -> entry.containsAll(fieldNameList));
    }

    private Map<String, List<FieldName>> initializeMapListsWinnerChoices() {
        mapListsWinnerChoices.put("1", List.of(FieldName.A1, FieldName.B1, FieldName.C1));
        mapListsWinnerChoices.put("2", List.of(FieldName.A2, FieldName.B2, FieldName.C2));
        mapListsWinnerChoices.put("3", List.of(FieldName.A3, FieldName.B3, FieldName.C3));
        mapListsWinnerChoices.put("4", List.of(FieldName.A1, FieldName.B2, FieldName.C3));
        mapListsWinnerChoices.put("5", List.of(FieldName.A3, FieldName.B2, FieldName.C1));
        mapListsWinnerChoices.put("6", List.of(FieldName.A1, FieldName.A2, FieldName.A3));
        mapListsWinnerChoices.put("7", List.of(FieldName.B1, FieldName.B2, FieldName.B3));
        mapListsWinnerChoices.put("8", List.of(FieldName.C1, FieldName.C2, FieldName.C3));
        return mapListsWinnerChoices;
    }
}
