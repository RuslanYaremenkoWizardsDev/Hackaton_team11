package com.example.server.tournament.controller;

import com.example.server.tournament.exception.controlleradvice.TournamentControllerAdvice;
import com.example.server.tournament.model.dto.TournamentDto;
import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.enums.Level;
import com.example.server.tournament.model.enums.Mode;
import com.example.server.tournament.model.enums.ScenatioOfTournament;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.services.CheckStartTimeTournament;
import com.example.server.tournament.services.CreateTournamentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import java.util.ArrayList;
import java.util.List;
import static com.example.server.tournament.util.Constants.ERROR_NAME;
import static com.example.server.usercredentials.utils.constants.Mappings.ADD_TOURNAMENT;
import static com.example.server.usercredentials.utils.constants.Mappings.GET_TOURNAMENT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TournamentControllerTest {
    private final CreateTournamentService createTournamentService = Mockito.mock(CreateTournamentService.class);
    private final CheckStartTimeTournament checkStartTimeTournament = Mockito.mock(CheckStartTimeTournament.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BindingResult bindingResult = Mockito.mock(BindingResult.class);
    private final TournamentController cut = new TournamentController(createTournamentService, checkStartTimeTournament);
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cut)
                .setControllerAdvice(TournamentControllerAdvice.class)
                .build();
    }

    @Test
    public void withoutExceptionSaveGameTest() throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        TournamentDto tournamentDto = new TournamentDto(1l, Status.IN_PROGRESS, "fff", "fff",
                Mode.CUP, "fffa", 123123123123l,
                1231231235345l, Level.MIDDLE, 32, ScenatioOfTournament.ONE_MATCH);
        TournamentEntity tournamentEntity = new TournamentEntity(
                null,
                tournamentDto.getStatus(),
                tournamentDto.getName(),
                tournamentDto.getTournamentDescription(),
                tournamentDto.getModeTournament(),
                tournamentDto.getPlace(),
                tournamentDto.getDateStartTournament(),
                tournamentDto.getDateLastRegistrationOnTournament(),
                tournamentDto.getLevel(),
                tournamentDto.getNumberOfPlayer(),
                tournamentDto.getScenarioOfTournament()
        );
        List<TournamentEntity> tournamentEntityList = new ArrayList<>();
        Mockito.doNothing().when(createTournamentService).saveGame(tournamentEntity);
        Mockito.when(createTournamentService.getAllTournament()).thenReturn(tournamentEntityList);
        mockMvc.perform(post(ADD_TOURNAMENT).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(tournamentDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(tournamentEntityList)));
    }

    @Test
    public void exceptionSaveGameTest() throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        TournamentDto tournamentDto = new TournamentDto(1l, Status.IN_PROGRESS, "", "fff",
                Mode.CUP, "fffa", 123123123123l,
                1231231235345l, Level.MIDDLE, 32, ScenatioOfTournament.ONE_MATCH);
        mockMvc.perform(post(ADD_TOURNAMENT).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(tournamentDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(ERROR_NAME));
    }

    @Test
    public void getAllTournamentsTest() throws Exception {
        Mockito.doNothing().when(checkStartTimeTournament).run();
        List<TournamentEntity> tournamentEntityList = new ArrayList<>();
        mockMvc.perform(post(GET_TOURNAMENT).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(tournamentEntityList)));
    }
}