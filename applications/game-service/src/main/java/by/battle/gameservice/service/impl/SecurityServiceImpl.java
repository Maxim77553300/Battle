package by.battle.gameservice.service.impl;

import by.battle.common.RoleName;
import by.battle.errorhandler.exception.ResourceNotFoundException;
import by.battle.gameservice.dto.BaseDto;
import by.battle.gameservice.dto.GameDto;
import by.battle.gameservice.dto.MoveDto;
import by.battle.gameservice.entity.Game;
import by.battle.gameservice.mapper.GameMapper;
import by.battle.gameservice.service.GameService;
import by.battle.gameservice.service.SecurityService;
import by.battle.security.util.AuthUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final GameService gameService;

    private final GameMapper gameMapper;

    @Override
    public boolean isManageableGame(GameDto gameDto) {
        return isUserParticipantGame(gameDto);
    }

    @Override
    public boolean isManageableGame(String id) {
        Game game = findGameFromDb(id);
        return isAdmin() || isUserParticipantGame(gameMapper.mapToDto(game));
    }

    @Override
    public boolean isManageableGame(MoveDto moveDto) {
        Game game = findGameFromDb(moveDto.getGameId());
        return isUserParticipantGame(gameMapper.mapToDto(game));
    }

    private boolean isUserParticipantGame(GameDto gameDto) {
        return gameDto.getUsers().stream().map(BaseDto::getId)
                .collect(Collectors.toList()).contains(AuthUtility.getCurrentUserId());
    }

    private boolean isAdmin() {
        return AuthUtility.getCurrentRoleNames().contains(RoleName.ADMIN);
    }

    private Game findGameFromDb(String id) {
        return gameService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Game does not exist!"));
    }
}
