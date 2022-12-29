package by.battle.battleservice.service.impl;

import by.battle.battleservice.entity.ResultUser;
import by.battle.battleservice.repository.ResultRepository;
import by.battle.battleservice.service.ResultService;
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
