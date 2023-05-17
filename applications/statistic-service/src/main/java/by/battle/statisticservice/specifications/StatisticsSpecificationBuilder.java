package by.battle.statisticservice.specifications;

import by.battle.database.specification.BaseSpecification;
import by.battle.database.specification.CompositeType;
import by.battle.statisticservice.entity.StatisticsUser;
import by.battle.statisticservice.entity.StatisticsUser_;

import java.time.LocalDateTime;

public class StatisticsSpecificationBuilder extends BaseSpecification<StatisticsUser> {
    public StatisticsSpecificationBuilder(CompositeType compositeType) {
        super(compositeType);
    }

    public StatisticsSpecificationBuilder hasUserId(String userId) {
        addSpecificationIfNotEmptyOrNull(
                userId,
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(StatisticsUser_.USER_ID), userId));
        return this;
    }

    public StatisticsSpecificationBuilder likeUserName(String userName) {
        addSpecificationIfNotEmptyOrNull(
                userName,
                (root, query, criteriaBuilder) -> criteriaBuilder
                        .like(root.get(StatisticsUser_.USER_NAME), "%" + userName + "%")
        );
        return this;
    }

    public StatisticsSpecificationBuilder greaterThanStartDate(LocalDateTime startDate) {
        addSpecificationIfNotEmptyOrNull(startDate, (root, query, criteriaBuilder) -> criteriaBuilder
                .greaterThanOrEqualTo(root.get(StatisticsUser_.CREATED_AT), startDate));
        return this;
    }

    public StatisticsSpecificationBuilder lessThanStartDate(LocalDateTime endDate) {
        addSpecificationIfNotEmptyOrNull(endDate, (root, query, criteriaBuilder) -> criteriaBuilder
                .lessThanOrEqualTo(root.get(StatisticsUser_.CREATED_AT), endDate));
        return this;
    }

    public StatisticsSpecificationBuilder greaterThanNumberOfGames(Integer startNumberOfGames) {
        addSpecificationIfNotEmptyOrNull(startNumberOfGames, (root, query, criteriaBuilder) -> criteriaBuilder
                .greaterThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_GAMES), startNumberOfGames));
        return this;
    }

    public StatisticsSpecificationBuilder lessThanNumberOfGames(Integer endNumberOfGames) {
        addSpecificationIfNotEmptyOrNull(endNumberOfGames, (root, query, criteriaBuilder) -> criteriaBuilder
                .lessThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_GAMES), endNumberOfGames));
        return this;
    }

    public StatisticsSpecificationBuilder greaterThanNumberOfWins(Integer startNumberOfWins) {
        addSpecificationIfNotEmptyOrNull(startNumberOfWins, (root, query, criteriaBuilder) -> criteriaBuilder
                .greaterThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_WINS), startNumberOfWins));
        return this;
    }

    public StatisticsSpecificationBuilder lessThanNumberOfWins(Integer endNumberOfWins) {
        addSpecificationIfNotEmptyOrNull(endNumberOfWins, (root, query, criteriaBuilder) -> criteriaBuilder
                .lessThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_WINS), endNumberOfWins));
        return this;
    }

    public StatisticsSpecificationBuilder greaterThanNumberOfDraw(Integer startNumberOfDraw) {
        addSpecificationIfNotEmptyOrNull(startNumberOfDraw, (root, query, criteriaBuilder) -> criteriaBuilder
                .greaterThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_DRAW), startNumberOfDraw));
        return this;
    }

    public StatisticsSpecificationBuilder lessThanNumberOfDraw(Integer endNumberOfDraw) {
        addSpecificationIfNotEmptyOrNull(endNumberOfDraw, (root, query, criteriaBuilder) -> criteriaBuilder
                .lessThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_DRAW), endNumberOfDraw));
        return this;
    }

    public StatisticsSpecificationBuilder greaterThanNumberOfLooses(Integer startNumberOfLooses) {
        addSpecificationIfNotEmptyOrNull(startNumberOfLooses, (root, query, criteriaBuilder) -> criteriaBuilder
                .greaterThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_LOOSES), startNumberOfLooses));
        return this;
    }

    public StatisticsSpecificationBuilder lessThanNumberOfLooses(Integer endNumberOfLooses) {
        addSpecificationIfNotEmptyOrNull(endNumberOfLooses, (root, query, criteriaBuilder) -> criteriaBuilder
                .lessThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_LOOSES), endNumberOfLooses));
        return this;
    }

    public StatisticsSpecificationBuilder greaterThanNumberOfMoves(Integer startNumberOfMoves) {
        addSpecificationIfNotEmptyOrNull(startNumberOfMoves, (root, query, criteriaBuilder) -> criteriaBuilder
                .greaterThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_MOVES), startNumberOfMoves));
        return this;
    }

    public StatisticsSpecificationBuilder lessThanNumberOfMoves(Integer endNumberOfMoves) {
        addSpecificationIfNotEmptyOrNull(endNumberOfMoves, ((root, query, criteriaBuilder) -> criteriaBuilder
                .lessThanOrEqualTo(root.get(StatisticsUser_.NUMBER_OF_MOVES), endNumberOfMoves)));
        return this;
    }
}
