package by.battle.battleservice.service;

import by.battle.battleservice.entity.Game;
import by.battle.battleservice.entity.Move;

public interface GameService {

    Game create(Game game);

    Game getById(String id);

    Game play(Move move);
}
