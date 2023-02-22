package by.battle.statisticservice.repository;

import by.battle.statisticservice.entity.StatisticsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsUser, String> {

    Optional<StatisticsUser> findByUserId(String userId);

    void deleteByUserId(String userId);
}
