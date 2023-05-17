package by.battle.statisticservice.service;

import by.battle.statisticservice.dto.GameDto;
import by.battle.statisticservice.entity.StatisticsUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface StatisticsService {

    Optional<StatisticsUser> findByUserId(String userId);

    List<StatisticsUser> findAllStatistics();

    List<StatisticsUser> updateAllStatistics(List<StatisticsUser> statisticsUsers);

    StatisticsUser update(StatisticsUser statisticsUser);

    StatisticsUser createStatisticsUser(GameDto gameDto, Integer index);

    Page<StatisticsUser> searchAllStatistics(Specification<StatisticsUser> buildSpecification, Pageable pageable);
}
