package by.battle.gameservice.service;


import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.ResultUser;
import by.battle.gameservice.entity.User;

import java.util.Optional;

public interface ResultService {

    ResultUser save(ResultUser resultUser);

    Optional<ResultUser> findByUserAndGame(User user, Game game);
}
