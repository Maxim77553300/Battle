package by.battle.gameservice.mapper;


import by.battle.gameservice.dto.PlayerFigureDto;
import by.battle.gameservice.entity.PlayerFigure;
import by.battle.gameservice.entity.User;
import by.battle.gameservice.service.UserService;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PlayerFigureMapper {

    @Autowired
    private UserService userService;

    public abstract PlayerFigureDto mapToDto(PlayerFigure playerFigureUser);

    public abstract PlayerFigure mapFromDto(PlayerFigureDto playerFigureUserDto);

    @AfterMapping
    public void after(@MappingTarget PlayerFigure playerFigure, PlayerFigureDto playerFigureDto) {
        playerFigure.setUser(userService.findByName(playerFigureDto.getUserName())
                .orElseGet(() -> new User().setName(playerFigureDto.getUserName())));
    }

    @AfterMapping
    public void after(@MappingTarget PlayerFigureDto playerFigureDto, PlayerFigure playerFigure) {
        playerFigureDto.setUserName(playerFigure.getUser().getName());
        playerFigureDto.setGameId(playerFigure.getGame().getId());
        playerFigureDto.setUserId(userService.findByName(playerFigure.getUserName()).get().getId());
    }
}
