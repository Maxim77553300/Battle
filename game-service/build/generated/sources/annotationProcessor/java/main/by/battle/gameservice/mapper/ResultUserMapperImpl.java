package by.battle.gameservice.mapper;

import by.battle.gameservice.dto.ResultUserDto;
import by.battle.gameservice.entity.ResultUser;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-01T13:12:25+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.1.jar, environment: Java 11.0.14.1 (Amazon.com Inc.)"
)
@Component
public class ResultUserMapperImpl extends ResultUserMapper {

    @Override
    public ResultUserDto mapToDto(ResultUser resultUser) {
        if ( resultUser == null ) {
            return null;
        }

        ResultUserDto resultUserDto = new ResultUserDto();

        resultUserDto.setId( resultUser.getId() );
        resultUserDto.setCreatedAt( resultUser.getCreatedAt() );
        resultUserDto.setResult( resultUser.getResult() );

        after( resultUserDto, resultUser );

        return resultUserDto;
    }

    @Override
    public ResultUser mapFromDto(ResultUserDto resultUserDto) {
        if ( resultUserDto == null ) {
            return null;
        }

        ResultUser resultUser = new ResultUser();

        resultUser.setId( resultUserDto.getId() );
        resultUser.setCreatedAt( resultUserDto.getCreatedAt() );
        resultUser.setResult( resultUserDto.getResult() );

        return resultUser;
    }
}
