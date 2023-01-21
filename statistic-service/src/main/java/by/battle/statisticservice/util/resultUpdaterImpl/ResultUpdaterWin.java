package by.battle.statisticservice.util.resultUpdaterImpl;

import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.util.ResultUpdater;
import org.springframework.stereotype.Component;

@Component("WIN")
public class ResultUpdaterWin implements ResultUpdater {

    @Override
    public StatisticsUser setResult(StatisticsUser statisticsUser) {
        return statisticsUser.setNumberOfWins(statisticsUser.getNumberOfWins() + 1);
    }
}
