package by.battle.statisticservice.mapper;


import by.battle.statisticservice.dto.GameDto;
import by.battle.statisticservice.dto.ResultUserDto;
import by.battle.statisticservice.dto.StatisticsUserDto;
import by.battle.statisticservice.entity.Result;
import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.util.ResultUpdater;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Mapper(componentModel = "spring")
public abstract class StatisticUserMapper {

    private final Map<String, StatisticsUser> mapIdUserToStatistics = new HashMap<>();

    @Autowired
    private Map<String, ResultUpdater> resultUpdaterMap;

    public abstract StatisticsUserDto mapToDto(StatisticsUser statisticsUser);

    public List<StatisticsUser> mapFromDto(GameDto gameDto) {
        return List.of(mapToStatisticsUserByIndex(gameDto, 0), mapToStatisticsUserByIndex(gameDto, 1));
    }

    private StatisticsUser mapToStatisticsUserByIndex(GameDto gameDto, Integer index) {
        setResultListIfNotExists(gameDto);
        StatisticsUser statisticsUser = getStatisticsUser(gameDto, index);
        setStatisticUser(statisticsUser, gameDto, index);
        mapIdUserToStatistics.put(statisticsUser.getUserId(), statisticsUser);
        ResultUpdater resultUpdater = resultUpdaterMap.get(getResult(gameDto, index).name());
        return resultUpdater.setResult(statisticsUser);
    }

    private List<ResultUserDto> createListResultUserDto(GameDto gameDto) {
        return List.of(new ResultUserDto()
                        .setUserId(gameDto.getUsers().get(0).getId())
                        .setGameId(gameDto.getId()),
                new ResultUserDto()
                        .setUserId(gameDto.getUsers().get(1).getId())
                        .setGameId(gameDto.getId()));
    }

    private GameDto setResultListIfNotExists(GameDto gameDto) {
        return gameDto.getResults() != null ? gameDto : gameDto
                .setResults(List.of(createListResultUserDto(gameDto).get(0).setResult(Result.DRAW),
                        createListResultUserDto(gameDto).get(1).setResult(Result.DRAW)));
    }

    private Result getResult(GameDto gameDto, Integer index) {
        return gameDto.getResults().stream()
                .filter(it -> it.getUserId().equals(gameDto.getUsers().get(index).getId()))
                .collect(Collectors.toList()).get(0).getResult();
    }

    private StatisticsUser getStatisticsUser(GameDto gameDto, Integer index) {
        StatisticsUser statisticsUser = mapIdUserToStatistics.get(gameDto.getUsers().get(index).getId());
        return statisticsUser == null ? createStatistics() : statisticsUser;
    }

    private Integer getUserMovesThisGame(GameDto gameDto) {
        return gameDto.getMoves() == null ? 0 : gameDto
                .getMoves()
                .stream()
                .filter(it -> it.getUserId().equals(gameDto.getUsers().get(0).getId()))
                .map(it -> 1)
                .reduce(0, Integer::sum);
    }

    private StatisticsUser setStatisticUser(StatisticsUser statisticsUser, GameDto gameDto, Integer index) {
        statisticsUser
                .setUserId(gameDto.getUsers().get(index).getId())
                .setUserName(gameDto.getUsers().get(index).getName())
                .setNumberOfGames(statisticsUser.getNumberOfGames() + 1)
                .setNumberOfMoves(statisticsUser.getNumberOfMoves() + getUserMovesThisGame(gameDto))
                .setNumberOfWins(statisticsUser.getNumberOfWins())
                .setNumberOfDraw(statisticsUser.getNumberOfDraw())
                .setNumberOfLooses(statisticsUser.getNumberOfLooses());
        return statisticsUser;
    }

    private StatisticsUser createStatistics() {
        return new StatisticsUser()
                .setNumberOfLooses(0)
                .setNumberOfDraw(0)
                .setNumberOfWins(0)
                .setNumberOfGames(0)
                .setNumberOfMoves(0);
    }
}
