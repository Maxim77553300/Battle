package by.battle.statisticservice.service.impl;

import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.repository.StatisticsRepository;
import by.battle.statisticservice.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsRepository statisticsRepository;

    @Override
    public Optional<StatisticsUser> findByUserId(String userId) {
        return statisticsRepository.findByUserId(userId);
    }

    @Override
    public List<StatisticsUser> findAllStatistics() {
        return statisticsRepository.findAll();
    }

    @Override
    public List<StatisticsUser> updateAllStatistics(List<StatisticsUser> statisticsUsers) {
        statisticsUsers.stream().map(this::update).collect(Collectors.toList());
        return findAllStatistics();
    }

    public StatisticsUser update(StatisticsUser statisticsUser) {
        StatisticsUser statisticsUserFromDb = statisticsRepository
                .findByUserId(statisticsUser.getUserId()).orElseGet(StatisticsUser::new);
        statisticsUserFromDb
                .setNumberOfMoves(statisticsUser.getNumberOfMoves())
                .setNumberOfWins(statisticsUser.getNumberOfWins())
                .setNumberOfLooses(statisticsUser.getNumberOfLooses())
                .setNumberOfGames(statisticsUser.getNumberOfGames())
                .setNumberOfDraw(statisticsUser.getNumberOfDraw())
                .setUserName(statisticsUser.getUserName())
                .setUserId(statisticsUser.getUserId());
        return statisticsRepository.save(statisticsUserFromDb);
    }
}
