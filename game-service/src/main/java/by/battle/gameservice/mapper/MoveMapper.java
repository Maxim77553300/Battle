package by.battle.gameservice.mapper;

import by.battle.errorhandler.exception.ResourceNotFoundException;
import by.battle.gameservice.dto.MoveDto;
import by.battle.gameservice.entity.Cell;
import by.battle.gameservice.entity.Move;
import by.battle.gameservice.repository.FieldPlaceRepository;
import by.battle.gameservice.repository.GameRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MoveMapper {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private FieldPlaceRepository fieldPlaceRepository;

    public abstract MoveDto mapToDto(Move move);

    public abstract Move mapFromDto(MoveDto moveDto);

    @AfterMapping
    public void after(@MappingTarget Move move, MoveDto moveDto) {
        move.setGame(gameRepository.findById(moveDto.getGameId())
                .orElseThrow(() -> new ResourceNotFoundException("Game not found")));
        Cell cell = fieldPlaceRepository
                .findByGameIdAndHorizontalIndexAndVerticalIndex(
                        move.getGame().getId(),
                        moveDto.getCell().getHorizontalIndex(),
                        moveDto.getCell().getVerticalIndex()
                );
        move.setCell(cell);
    }

    @AfterMapping
    public void after(@MappingTarget MoveDto moveDto, Move move) {
        moveDto.setGameId(move.getGame().getId());
        moveDto.setUserId(move.getUser().getId());
    }
}
