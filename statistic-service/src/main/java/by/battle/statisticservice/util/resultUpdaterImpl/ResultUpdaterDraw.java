package by.battle.statisticservice.util.resultUpdaterImpl;

import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.util.ResultUpdater;
import org.springframework.stereotype.Component;

@Component("DRAW")
public class ResultUpdaterDraw implements ResultUpdater {
    @Override
    public StatisticsUser setResult(StatisticsUser statisticsUser) {
        return statisticsUser.setNumberOfDraw(statisticsUser.getNumberOfDraw() + 1);
    }
}
