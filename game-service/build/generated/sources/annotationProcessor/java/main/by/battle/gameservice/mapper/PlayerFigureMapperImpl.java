package by.battle.gameservice.mapper;

import by.battle.gameservice.dto.PlayerFigureDto;
import by.battle.gameservice.entity.PlayerFigure;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T13:12:25+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class PlayerFigureMapperImpl extends PlayerFigureMapper {

    @Override
    public PlayerFigureDto mapToDto(PlayerFigure playerFigureUser) {
        if ( playerFigureUser == null ) {
            return null;
        }

        PlayerFigureDto playerFigureDto = new PlayerFigureDto();

        playerFigureDto.setFigure( playerFigureUser.getFigure() );
        playerFigureDto.setUserName( playerFigureUser.getUserName() );

        after( playerFigureDto, playerFigureUser );

        return playerFigureDto;
    }

    @Override
    public PlayerFigure mapFromDto(PlayerFigureDto playerFigureUserDto) {
        if ( playerFigureUserDto == null ) {
            return null;
        }

        PlayerFigure playerFigure = new PlayerFigure();

        playerFigure.setFigure( playerFigureUserDto.getFigure() );
        playerFigure.setUserName( playerFigureUserDto.getUserName() );

        after( playerFigure, playerFigureUserDto );

        return playerFigure;
    }
}
