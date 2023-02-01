package by.battle.gameservice.mapper;

import by.battle.gameservice.dto.GameDto;
import by.battle.gameservice.dto.MoveDto;
import by.battle.gameservice.dto.ResultUserDto;
import by.battle.gameservice.dto.UserDto;
import by.battle.gameservice.entity.Game;
import by.battle.gameservice.entity.Move;
import by.battle.gameservice.entity.PlayerFigure;
import by.battle.gameservice.entity.ResultUser;
import by.battle.gameservice.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T13:13:33+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class GameMapperImpl implements GameMapper {

    @Autowired
    private MoveMapper moveMapper;
    @Autowired
    private ResultUserMapper resultUserMapper;

    @Override
    public GameDto mapToDto(Game game) {
        if ( game == null ) {
            return null;
        }

        GameDto gameDto = new GameDto();

        gameDto.setId( game.getId() );
        gameDto.setCreatedAt( game.getCreatedAt() );
        gameDto.setUpdatedAt( game.getUpdatedAt() );
        gameDto.setStatus( game.getStatus() );
        gameDto.setSize( game.getSize() );
        gameDto.setUsers( userListToUserDtoList( game.getUsers() ) );
        gameDto.setMoves( moveListToMoveDtoList( game.getMoves() ) );
        gameDto.setResults( resultUserListToResultUserDtoList( game.getResults() ) );
        List<PlayerFigure> list3 = game.getPlayerFigures();
        if ( list3 != null ) {
            gameDto.setPlayerFigures( new ArrayList<PlayerFigure>( list3 ) );
        }

        return gameDto;
    }

    @Override
    public Game mapFromDto(GameDto gameDto) {
        if ( gameDto == null ) {
            return null;
        }

        Game game = new Game();

        game.setId( gameDto.getId() );
        game.setCreatedAt( gameDto.getCreatedAt() );
        game.setUpdatedAt( gameDto.getUpdatedAt() );
        game.setStatus( gameDto.getStatus() );
        game.setSize( gameDto.getSize() );
        game.setUsers( userDtoListToUserList( gameDto.getUsers() ) );
        game.setMoves( moveDtoListToMoveList( gameDto.getMoves() ) );
        game.setResults( resultUserDtoListToResultUserList( gameDto.getResults() ) );
        List<PlayerFigure> list3 = gameDto.getPlayerFigures();
        if ( list3 != null ) {
            game.setPlayerFigures( new ArrayList<PlayerFigure>( list3 ) );
        }

        return game;
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setCreatedAt( user.getCreatedAt() );
        userDto.setUpdatedAt( user.getUpdatedAt() );
        userDto.setName( user.getName() );

        return userDto;
    }

    protected List<UserDto> userListToUserDtoList(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<UserDto> list1 = new ArrayList<UserDto>( list.size() );
        for ( User user : list ) {
            list1.add( userToUserDto( user ) );
        }

        return list1;
    }

    protected List<MoveDto> moveListToMoveDtoList(List<Move> list) {
        if ( list == null ) {
            return null;
        }

        List<MoveDto> list1 = new ArrayList<MoveDto>( list.size() );
        for ( Move move : list ) {
            list1.add( moveMapper.mapToDto( move ) );
        }

        return list1;
    }

    protected List<ResultUserDto> resultUserListToResultUserDtoList(List<ResultUser> list) {
        if ( list == null ) {
            return null;
        }

        List<ResultUserDto> list1 = new ArrayList<ResultUserDto>( list.size() );
        for ( ResultUser resultUser : list ) {
            list1.add( resultUserMapper.mapToDto( resultUser ) );
        }

        return list1;
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDto.getId() );
        user.setCreatedAt( userDto.getCreatedAt() );
        user.setName( userDto.getName() );
        user.setUpdatedAt( userDto.getUpdatedAt() );

        return user;
    }

    protected List<User> userDtoListToUserList(List<UserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<User> list1 = new ArrayList<User>( list.size() );
        for ( UserDto userDto : list ) {
            list1.add( userDtoToUser( userDto ) );
        }

        return list1;
    }

    protected List<Move> moveDtoListToMoveList(List<MoveDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Move> list1 = new ArrayList<Move>( list.size() );
        for ( MoveDto moveDto : list ) {
            list1.add( moveMapper.mapFromDto( moveDto ) );
        }

        return list1;
    }

    protected List<ResultUser> resultUserDtoListToResultUserList(List<ResultUserDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ResultUser> list1 = new ArrayList<ResultUser>( list.size() );
        for ( ResultUserDto resultUserDto : list ) {
            list1.add( resultUserMapper.mapFromDto( resultUserDto ) );
        }

        return list1;
    }
}
