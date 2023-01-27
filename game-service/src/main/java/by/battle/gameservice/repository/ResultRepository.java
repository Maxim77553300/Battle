package by.battle.gameservice.repository;

import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.ResultUser;
import by.battle.gameservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<ResultUser, String> {

    Optional<ResultUser> findByUserAndGame(User user, Game game);
}
