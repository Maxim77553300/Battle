package by.battle.battleservice.service.impl;

import by.battle.battleservice.entity.Move;
import by.battle.battleservice.repository.MoveRepository;
import by.battle.battleservice.service.MoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoveServiceImpl implements MoveService {

    private final MoveRepository moveRepository;

    @Override
    public List<Move> findAllByGameId(String gameId) {
        return moveRepository.findAllByGameId(gameId);
    }

    @Override
    public Move save(Move move) {
        return moveRepository.save(move);
    }
}
