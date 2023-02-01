package by.battle.gameservice.mapper;

import by.battle.gameservice.dto.CellDto;
import by.battle.gameservice.dto.MoveDto;
import by.battle.gameservice.entity.Cell;
import by.battle.gameservice.entity.Move;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T13:13:33+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class MoveMapperImpl extends MoveMapper {

    @Override
    public MoveDto mapToDto(Move move) {
        if ( move == null ) {
            return null;
        }

        MoveDto moveDto = new MoveDto();

        moveDto.setId( move.getId() );
        moveDto.setCreatedAt( move.getCreatedAt() );
        moveDto.setCell( cellToCellDto( move.getCell() ) );

        after( moveDto, move );

        return moveDto;
    }

    @Override
    public Move mapFromDto(MoveDto moveDto) {
        if ( moveDto == null ) {
            return null;
        }

        Move move = new Move();

        move.setId( moveDto.getId() );
        move.setCreatedAt( moveDto.getCreatedAt() );
        move.setCell( cellDtoToCell( moveDto.getCell() ) );

        after( move, moveDto );

        return move;
    }

    protected CellDto cellToCellDto(Cell cell) {
        if ( cell == null ) {
            return null;
        }

        CellDto cellDto = new CellDto();

        cellDto.setHorizontalIndex( cell.getHorizontalIndex() );
        cellDto.setVerticalIndex( cell.getVerticalIndex() );

        return cellDto;
    }

    protected Cell cellDtoToCell(CellDto cellDto) {
        if ( cellDto == null ) {
            return null;
        }

        Cell cell = new Cell();

        cell.setHorizontalIndex( cellDto.getHorizontalIndex() );
        cell.setVerticalIndex( cellDto.getVerticalIndex() );

        return cell;
    }
}
