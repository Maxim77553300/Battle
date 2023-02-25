package by.battle.statisticservice.updaters.resultUpdaterImpl;

import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.updaters.ResultUpdater;
import org.springframework.stereotype.Component;

@Component("WIN")
public class ResultUpdaterWin implements ResultUpdater {

    @Override
    public StatisticsUser setResult(StatisticsUser statisticsUser) {
        return statisticsUser.setNumberOfWins(statisticsUser.getNumberOfWins() + 1);
    }
}
