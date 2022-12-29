package by.battle.battleservice.repository;

import by.battle.battleservice.entity.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<Move, String> {

    List<Move> findAllByGameId(String id);
}
