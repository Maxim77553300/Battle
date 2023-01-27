package by.battle.gameservice.repository;

import by.battle.gameservice.entity.FieldPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldPlaceRepository extends JpaRepository<FieldPlace, String> {
    FieldPlace findByGameIdAndHorizontalIndexAndVerticalIndex(String gameId, Integer horizontalIndex, Integer verticalIndex);
}
