package by.battle.gameservice.service;


import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.Move;

import java.util.Optional;

public interface GameService {

    Game create(Game game);

    Optional<Game> getById(String id);

    Game play(Move move);
}
