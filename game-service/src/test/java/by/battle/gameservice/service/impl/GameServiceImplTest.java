package by.battle.gameservice.service.impl;

import by.battle.gameservice.dto.GameDto;
import by.battle.gameservice.entity.GameStatus;
import by.battle.gameservice.entity.Result;
import by.battle.gameservice.mapper.UserMapper;
import by.battle.gameservice.util.helper.GameHelper;
import by.battle.gameservice.util.helper.UserHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class GameServiceImplTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GameHelper gameHelper;
    @Autowired
    private UserHelper userHelper;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void createGame_shouldReturnStatusCreated_whenGameDtoIsValid() throws Exception {
        final GameDto gameDto = gameHelper.createValidGameDto();

        mockMvc.perform(post("/games")
                        .content(objectMapper.writeValueAsString(gameDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.updatedAt").isNotEmpty())
                .andExpect(jsonPath("$.status").value(GameStatus.STARTED.name()))
                .andExpect(jsonPath("$.size").value(gameDto.getSize()))
                .andExpect(jsonPath("$.users").isArray())
                .andExpect(jsonPath("$.users", hasSize(2)))
                .andExpect(jsonPath("$.users[0].id").isNotEmpty())
                .andExpect(jsonPath("$.users[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.users[0]").isNotEmpty())
                .andExpect(jsonPath("$.users[0].updatedAt").isNotEmpty())
                .andExpect(jsonPath("$.users[0].name").value(gameDto.getUsers().get(0).getName()))
                .andExpect(jsonPath("$.users[1].id").isNotEmpty())
                .andExpect(jsonPath("$.users[1].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.users[1]").isNotEmpty())
                .andExpect(jsonPath("$.users[1].updatedAt").isNotEmpty())
                .andExpect(jsonPath("$.users[1].name").value(gameDto.getUsers().get(1).getName()))
                .andExpect(jsonPath("$.results").isArray())
                .andExpect(jsonPath("$.results", hasSize(2)))
                .andExpect(jsonPath("$.results[0].id").isNotEmpty())
                .andExpect(jsonPath("$.results[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.results[0].result").value(Result.IN_PROGRESS.name()))
                .andExpect(jsonPath("$.results[0].userId").isNotEmpty())
                .andExpect(jsonPath("$.results[0].gameId").isNotEmpty())
                .andExpect(jsonPath("$.results[1].id").isNotEmpty())
                .andExpect(jsonPath("$.results[1].createdAt").isNotEmpty())
                .andExpect(jsonPath("$.results[1].result").value(Result.IN_PROGRESS.name()))
                .andExpect(jsonPath("$.results[1].userId").isNotEmpty())
                .andExpect(jsonPath("$.results[1].gameId").isNotEmpty())
                .andExpect(jsonPath("$.playerFigures").isArray())
                .andExpect(jsonPath("$.playerFigures").isNotEmpty())
                .andExpect(jsonPath("$.playerFigures", hasSize(2)))
                .andExpect(jsonPath("$.playerFigures[0].figure")
                        .value(gameDto.getPlayerFigures().get(0).getFigure().name()))
                .andExpect(jsonPath("playerFigures[0].userName").value(gameDto.getPlayerFigures().get(0).getUserName()))
                .andExpect(jsonPath("playerFigures[1].figure")
                        .value(gameDto.getPlayerFigures().get(1).getFigure().name()))
                .andExpect(jsonPath("playerFigures[1].userName").value(gameDto.getPlayerFigures().get(1).getUserName()));
    }

    @Test
    public void createGame_shouldReturnStatusBadRequest_whenSizeIsNotValid() throws Exception {
        final GameDto gameDto = gameHelper.createValidGameDto();
        gameDto.setSize(-3);

        mockMvc.perform(post("/games")
                        .content(objectMapper.writeValueAsString(gameDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createGame_shouldReturnStatusBadRequest_whenUsersListIsEmpty() throws Exception {
        final GameDto gameDto = gameHelper.createValidGameDto();
        gameDto.setUsers(Collections.emptyList());

        mockMvc.perform(post("/games")
                        .content(objectMapper.writeValueAsString(gameDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createGame_shouldReturnStatusBadRequest_whenPlayerFiguresIsEmptyList() throws Exception {
        final GameDto gameDto = gameHelper.createValidGameDto();
        gameDto.setPlayerFigures(Collections.emptyList());

        mockMvc.perform(post("/games")
                        .content(objectMapper.writeValueAsString(gameDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createGame_shouldReturnStatusBadRequest_whenPlayerFiguresAreEquals() throws Exception {
        final GameDto gameDto = gameHelper.createValidGameDto();
        gameDto.setPlayerFigures(gameHelper.createPlayerFigureDtoListWithTheSameFigure());

        mockMvc.perform(post("/games")
                        .content(objectMapper.writeValueAsString(gameDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createGame_shouldReturnStatusInternalServerError_whenUserNamesAreEquals() throws Exception {
        final GameDto gameDto = gameHelper.createValidGameDto();
        gameDto.setUsers(List.of(userHelper.createValidUserFirst(), userHelper.createValidUserFirst()).stream()
                .map(it -> userMapper.mapToDto(it)).collect(Collectors.toList()));

        mockMvc.perform(post("/games")
                        .content(objectMapper.writeValueAsString(gameDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("userMessage").isNotEmpty());
    }

    @Test
    public void createGame_shouldReturnStatusInternalServerError_whenUserNamesInPlayerFigureAreEquals() throws Exception {
        final GameDto gameDto = gameHelper.createValidGameDto();
        gameDto.setPlayerFigures(gameHelper.createPlayerFigureListWithTheSameNames());

        mockMvc.perform(post("/games")
                        .content(objectMapper.writeValueAsString(gameDto))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("userMessage").isNotEmpty());
    }
}