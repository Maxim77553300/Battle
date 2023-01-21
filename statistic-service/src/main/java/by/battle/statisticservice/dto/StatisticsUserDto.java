package by.battle.statisticservice.dto;

import lombok.Data;

@Data
public class StatisticsUserDto {

    private String id;
    private String userId;
    private String userName;
    private Integer numberOfGames;
    private Integer numberOfWins;
    private Integer numberOfDraw;
    private Integer numberOfLooses;
    private Integer numberOfMoves;

}
