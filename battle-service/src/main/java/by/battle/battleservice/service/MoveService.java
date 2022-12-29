package by.battle.battleservice.service;

import by.battle.battleservice.entity.Move;

import java.util.List;

public interface MoveService {

    List<Move> findAllByGameId(String gameId);

    Move save(Move move);
}
