package by.battle.battleservice.util;

import by.battle.battleservice.entity.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WinnerCombinationStorageImpl implements WinnerCombinationStorage {

    private Map<String, List<Field>> mapListsWinnerChoices;

    public boolean checkWin(List<Field> fieldList) {
        return mapListsWinnerChoices.values().stream().anyMatch(entry -> entry.containsAll(fieldList));
    }

    private Map<String, List<Field>> initializeMapListsWinnerChoices() {
        // mapListsWinnerChoices = new HashMap<>();
        mapListsWinnerChoices.put("1", List.of(Field.A1, Field.B1, Field.C1));
        mapListsWinnerChoices.put("2", List.of(Field.A2, Field.B2, Field.C2));
        mapListsWinnerChoices.put("3", List.of(Field.A3, Field.B3, Field.C3));
        mapListsWinnerChoices.put("4", List.of(Field.A1, Field.B2, Field.C3));
        mapListsWinnerChoices.put("5", List.of(Field.A3, Field.B2, Field.C1));
        mapListsWinnerChoices.put("6", List.of(Field.A1, Field.A2, Field.A3));
        mapListsWinnerChoices.put("7", List.of(Field.B1, Field.B2, Field.B3));
        mapListsWinnerChoices.put("8", List.of(Field.C1, Field.C2, Field.C3));
        return mapListsWinnerChoices;
    }
}
