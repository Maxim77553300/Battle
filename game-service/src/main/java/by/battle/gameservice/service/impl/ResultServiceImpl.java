package by.battle.gameservice.service.impl;

import by.battle.gameservice.entity.ResultUser;
import by.battle.gameservice.repository.ResultRepository;
import by.battle.gameservice.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Override
    public ResultUser save(ResultUser resultUser) {
        return resultRepository.save(resultUser);
    }
}
