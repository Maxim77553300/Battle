package by.battle.gameservice.repository;

import by.battle.gameservice.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldPlaceRepository extends JpaRepository<Cell, String> {
    Cell findByGameIdAndHorizontalIndexAndVerticalIndex(String gameId, Integer horizontalIndex, Integer verticalIndex);
}
