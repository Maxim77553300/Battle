package by.battle.battleservice.service;

import by.battle.battleservice.entity.Game;
import by.battle.battleservice.entity.GameStatus;
import by.battle.battleservice.entity.Move;
import by.battle.battleservice.entity.Result;
import by.battle.battleservice.entity.ResultUser;
import by.battle.battleservice.entity.User;
import by.battle.battleservice.exception.FieldNotFreeException;
import by.battle.battleservice.repository.GameRepository;
import by.battle.battleservice.repository.MoveRepository;
import by.battle.battleservice.repository.ResultRepository;
import by.battle.battleservice.repository.UserRepository;
import by.battle.battleservice.util.FakeSecurityHolder;
import by.battle.battleservice.util.WinnerCombinationStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final WinnerCombinationStorage winnerCombinationStorage;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final FakeSecurityHolder fakeSecurityHolder;
    private final ResultRepository resultRepository;
    private final MoveRepository moveRepository;

    @Override
    public Game create(Game game) {
        game.setName(String.format("Battle %s VS %s", game.getUsers().get(0).getName(), game.getUsers().get(1).getName()));
        game.setStatus(GameStatus.STARTED);
        return gameRepository.save(game);
    }

    @Override
    public Game getById(String id) {
        return gameRepository.findById(id).get();
    }

    @Override
    public Game play(Move move) {
        System.out.println(move);

        String userId = fakeSecurityHolder.getTestUserOneId();
        checkFreeField(move, userId);
        move.setUser(userRepository.findById(userId).get());
        Game game = gameRepository.findById(move.getGame().getId()).get();
        moveRepository.save(move);
        if (checkWinner(game)) {
            game.setStatus(GameStatus.FINISHED);
            move.getUser().addResultToList(new ResultUser().setResult(Result.WIN));
            User userLooser = game.getUsers().stream().filter(it -> !it.equals(move.getUser())).findFirst().get();
            saveResultLoose(userLooser);
            return gameRepository.save(game);
        }
        return gameRepository.save(game);
    }

    private void checkFreeField(Move move, String userId) {
        if (moveRepository.findByUserId(userId).orElse(new Move()).equals(move.getField())) {
            throw new FieldNotFreeException(move.getField().getFieldName().name());
        }
    }

    private boolean checkWinner(Game game) {
        return winnerCombinationStorage
                .checkWin(game.getMoves().stream().map(it -> it.getField().getFieldName()).collect(Collectors.toList()));

    }

    private void saveResultLoose(User userLooser) {
        resultRepository.save(new ResultUser().setResult(Result.LOOSE).setUser(userLooser));
    }
}
