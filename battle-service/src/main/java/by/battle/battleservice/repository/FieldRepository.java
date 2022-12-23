package by.battle.battleservice.repository;

import by.battle.battleservice.entity.Field;
import by.battle.battleservice.entity.FieldName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, String> {

    Field findByFieldName(FieldName fieldName);
}
