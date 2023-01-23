package by.battle.gameservice.repository;

import by.battle.gameservice.entity.Field;
import by.battle.gameservice.entity.FieldName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, String> {

    Field findByFieldName(FieldName fieldName);
}
