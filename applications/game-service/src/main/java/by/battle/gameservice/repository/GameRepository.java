package by.battle.gameservice.repository;

import by.battle.gameservice.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
}
