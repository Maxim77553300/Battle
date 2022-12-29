package by.battle.battleservice.repository;

import by.battle.battleservice.entity.ResultUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<ResultUser, String> {
}
