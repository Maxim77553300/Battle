package by.battle.gameservice.mapper;

import by.battle.gameservice.dto.MoveDto;
import by.battle.gameservice.entity.Move;
import by.battle.gameservice.repository.FieldRepository;
import by.battle.gameservice.repository.GameRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MoveDtoMapper {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private FieldRepository fieldRepository;

    public abstract MoveDto mapToDto(Move move);

    public abstract Move mapFromDto(MoveDto moveDto);

    @AfterMapping
    public void after(@MappingTarget Move move, MoveDto moveDto) {
        move.setGame(gameRepository.findById(moveDto.getGameId()).orElse(null));
        move.setField(fieldRepository.findByFieldName(moveDto.getFieldName()));
    }

    @AfterMapping
    public void after(@MappingTarget MoveDto moveDto, Move move) {
        moveDto.setGameId(move.getGame().getId());
        moveDto.setFieldName(move.getField().getFieldName());
        moveDto.setUserId(move.getUser().getId());
    }
}
