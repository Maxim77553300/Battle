package by.battle.battleservice.mapper;

import by.battle.battleservice.dto.MoveDto;
import by.battle.battleservice.entity.Move;
import by.battle.battleservice.repository.FieldRepository;
import by.battle.battleservice.repository.GameRepository;
import by.battle.battleservice.repository.UserRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class MoveDtoMapper {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FieldRepository fieldRepository;

    public abstract MoveDto mapToDto(Move move);

    public abstract Move mapFromDto(MoveDto moveDto);

    @AfterMapping
    public void after(@MappingTarget Move move, MoveDto moveDto) {
        move.setGame(gameRepository.findById(moveDto.getGameId()).orElse(null));
        move.setField(fieldRepository.findByFieldName(moveDto.getFieldName()));
    }
}
