package by.battle.statisticservice.controller;

import by.battle.statisticservice.dto.GameDto;
import by.battle.statisticservice.dto.StatisticsUserDto;
import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.mapper.StatisticUserMapper;
import by.battle.statisticservice.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final StatisticUserMapper statisticUserMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StatisticsUserDto> findAllStatistics() {
        return statisticsService.findAllStatistics().stream().map(statisticUserMapper::mapToDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StatisticsUserDto> updateStatistics(@RequestBody List<GameDto> gameDtos) {
        List<StatisticsUser> statisticsUsers = gameDtos.stream()
                .map(statisticUserMapper::mapFromDto)
                .flatMap(Collection::stream).collect(Collectors.toList());
        statisticUserMapper.getMapIdUserToStatistics().clear();
        return statisticsService.updateAllStatistics(statisticsUsers).stream()
                .map(statisticUserMapper::mapToDto).collect(Collectors.toList());
    }
}
