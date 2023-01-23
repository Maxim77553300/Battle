package by.battle.gameservice.service;


import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.Move;

public interface GameService {

    Game create(Game game);

    Game getById(String id);

    Game play(Move move);
}
