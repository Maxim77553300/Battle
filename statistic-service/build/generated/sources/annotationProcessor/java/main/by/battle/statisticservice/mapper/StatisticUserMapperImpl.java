package by.battle.statisticservice.mapper;

import by.battle.statisticservice.dto.StatisticsUserDto;
import by.battle.statisticservice.entity.StatisticsUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-01-30T18:13:26+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class StatisticUserMapperImpl extends StatisticUserMapper {

    @Override
    public StatisticsUserDto mapToDto(StatisticsUser statisticsUser) {
        if ( statisticsUser == null ) {
            return null;
        }

        StatisticsUserDto statisticsUserDto = new StatisticsUserDto();

        statisticsUserDto.setId( statisticsUser.getId() );
        statisticsUserDto.setUserId( statisticsUser.getUserId() );
        statisticsUserDto.setUserName( statisticsUser.getUserName() );
        statisticsUserDto.setNumberOfGames( statisticsUser.getNumberOfGames() );
        statisticsUserDto.setNumberOfWins( statisticsUser.getNumberOfWins() );
        statisticsUserDto.setNumberOfDraw( statisticsUser.getNumberOfDraw() );
        statisticsUserDto.setNumberOfLooses( statisticsUser.getNumberOfLooses() );
        statisticsUserDto.setNumberOfMoves( statisticsUser.getNumberOfMoves() );
        statisticsUserDto.setCreatedAt( statisticsUser.getCreatedAt() );

        return statisticsUserDto;
    }
}
