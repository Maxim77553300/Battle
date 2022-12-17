package by.battle.battleservice.service;

import by.battle.battleservice.entity.Game;
import by.battle.battleservice.entity.Move;
import by.battle.battleservice.repository.GameRepository;
import by.battle.battleservice.util.WinnerCombinationStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    //    private final List<Field> choiceListPlayer1;
//    private final List<Field> ChoiceListPlayer2;

    private final WinnerCombinationStorage winnerCombinationStorage;
    private final GameRepository gameRepository;

    @Override
    public Game create(Game game) {
        //Game game = new Game();
        return gameRepository.save(game);
    }

    @Override
    public Game getById(Game game) {
        return gameRepository.getById(game.getId());
    }

    @Override
    public Game play(Move move) {
        return null;
    }
}
