package by.battle.gameservice.service;


import by.battle.gameservice.entity.Move;

import java.util.List;

public interface MoveService {

    List<Move> findAllByGameId(String gameId);

    Move save(Move move);
}
