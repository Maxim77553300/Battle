package by.battle.statisticservice.util.resultUpdaterImpl;

import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.util.ResultUpdater;
import org.springframework.stereotype.Component;

@Component("LOOSE")
public class ResultUpdaterLoose implements ResultUpdater {

    @Override
    public StatisticsUser setResult(StatisticsUser statisticsUser) {
        return statisticsUser.setNumberOfLooses(statisticsUser.getNumberOfLooses() + 1);
    }
}
