package by.battle.statisticservice.dto;

import by.battle.database.specification.CompositeType;
import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.specifications.StatisticsSpecificationBuilder;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

@Data
public class StatisticsFilterDto {

    private String userId;
    private String userName;
    private Integer startNumberOfGames;
    private Integer endNumberOfGames;
    private Integer startNumberOfWins;
    private Integer endNumberOfWins;
    private Integer startNumberOfDraw;
    private Integer endNumberOfDraw;
    private Integer startNumberOfLooses;
    private Integer endNumberOfLooses;
    private Integer startNumberOfMoves;
    private Integer endNumberOfMoves;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Specification<StatisticsUser> buildSpecification(CompositeType compositeType) {
        return new StatisticsSpecificationBuilder(compositeType)
                .hasUserId(userId)
                .likeUserName(userName)
                .greaterThanNumberOfGames(startNumberOfGames)
                .lessThanNumberOfGames(endNumberOfGames)
                .greaterThanNumberOfWins(startNumberOfWins)
                .lessThanNumberOfWins(endNumberOfWins)
                .greaterThanNumberOfDraw(startNumberOfDraw)
                .lessThanNumberOfDraw(endNumberOfDraw)
                .greaterThanNumberOfLooses(startNumberOfLooses)
                .lessThanNumberOfLooses(endNumberOfLooses)
                .greaterThanNumberOfMoves(startNumberOfMoves)
                .lessThanNumberOfMoves(endNumberOfMoves)
                .greaterThanStartDate(startDate)
                .lessThanStartDate(endDate)
                .build();
    }
}
