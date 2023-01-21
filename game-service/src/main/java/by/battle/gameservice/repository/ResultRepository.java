package by.battle.gameservice.repository;

import by.battle.gameservice.entity.ResultUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<ResultUser, String> {
}
