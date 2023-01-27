package by.battle.gameservice.service.impl;

import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.ResultUser;
import by.battle.gameservice.entity.User;
import by.battle.gameservice.repository.ResultRepository;
import by.battle.gameservice.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    public ResultUser save(ResultUser resultUser) {
        return resultRepository.save(resultUser);
    }

    @Override
    public Optional<ResultUser> findByUserAndGame(User user, Game game) {
        return resultRepository.findByUserAndGame(user, game);
    }
}
