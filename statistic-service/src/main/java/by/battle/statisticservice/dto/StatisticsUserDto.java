package by.battle.statisticservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

}
