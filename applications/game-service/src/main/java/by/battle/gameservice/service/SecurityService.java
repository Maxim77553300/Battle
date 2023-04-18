package by.battle.gameservice.service;

import by.battle.gameservice.dto.GameDto;
import by.battle.gameservice.dto.MoveDto;

public interface SecurityService {

    boolean isManageableGame(GameDto gameDto);

    boolean isManageableGame(String id);

    boolean isManageableGame(MoveDto moveDto);
}
