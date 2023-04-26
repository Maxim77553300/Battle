package by.battle.gameservice.repository;

import by.battle.gameservice.entity.Game;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    @Override
    @EntityGraph(attributePaths = {"moves"})
    Optional<Game> findById(String id);
}
