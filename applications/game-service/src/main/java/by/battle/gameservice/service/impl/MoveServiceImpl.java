package by.battle.gameservice.service.impl;

import by.battle.errorhandler.exception.ResourceNotFoundException;
import by.battle.gameservice.entity.Cell;
import by.battle.gameservice.entity.Move;
import by.battle.gameservice.repository.FieldPlaceRepository;
import by.battle.gameservice.repository.GameRepository;
import by.battle.gameservice.repository.MoveRepository;
import by.battle.gameservice.service.MoveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MoveServiceImpl implements MoveService {

    private final GameRepository gameRepository;

    private final FieldPlaceRepository fieldPlaceRepository;

    private final MoveRepository moveRepository;

    @Override
    public List<Move> findAllByGameId(String gameId) {
        return moveRepository.findAllByGameId(gameId);
    }

    @Override
    @Transactional
    public Move createMove(Move move) {
        move.setGame(gameRepository.findById(move.getGame().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Game not found")));
        Cell cell = fieldPlaceRepository
                .findByGameIdAndHorizontalIndexAndVerticalIndex(
                        move.getGame().getId(),
                        move.getCell().getHorizontalIndex(),
                        move.getCell().getVerticalIndex()
                );
        return move.setCell(cell);
    }
}
