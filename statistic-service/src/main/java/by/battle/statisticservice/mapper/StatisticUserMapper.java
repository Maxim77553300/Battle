package by.battle.statisticservice.mapper;


import by.battle.statisticservice.dto.GameDto;
import by.battle.statisticservice.dto.StatisticsUserDto;
import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.service.StatisticsService;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Mapper(componentModel = "spring")
public abstract class StatisticUserMapper {

    @Autowired
    private StatisticsService statisticsService;

    public abstract StatisticsUserDto mapToDto(StatisticsUser statisticsUser);

    public List<StatisticsUser> mapFromDto(GameDto gameDto) {
        return List.of(statisticsService.createStatisticsUser(gameDto, 0),
                statisticsService.createStatisticsUser(gameDto, 1));
    }
}
