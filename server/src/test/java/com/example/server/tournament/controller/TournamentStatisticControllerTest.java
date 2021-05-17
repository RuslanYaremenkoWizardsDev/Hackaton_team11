package com.example.server.tournament.controller;

import com.example.server.tournament.exception.controlleradvice.TournamentControllerAdvice;
import com.example.server.tournament.model.dto.TournamentStatisticModel;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.repo.TournamentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

import static com.example.server.usercredentials.utils.constants.Mappings.GET_TOURNAMENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TournamentStatisticControllerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BindingResult bindingResult = Mockito.mock(BindingResult.class);
    private final TournamentRepo tournamentRepo = Mockito.mock(TournamentRepo.class);
    private final TournamentStatisticController cut = new TournamentStatisticController(tournamentRepo);
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cut)
                .setControllerAdvice(TournamentControllerAdvice.class)
                .build();
    }
    @Test
    public void getTournamentStatisticTest() throws Exception {
        TournamentStatisticModel tournamentStatisticModel = new TournamentStatisticModel(0,0,0,0);
       Mockito.when(tournamentRepo.findAll()).thenReturn(new ArrayList<>());
       Mockito.when(tournamentRepo.findAllByStatus(Status.FINISHED)).thenReturn(new ArrayList<>());
       Mockito.when(tournamentRepo.findAllByStatus(Status.ACTIVE)).thenReturn(new ArrayList<>());
       Mockito.when(tournamentRepo.findAllByStatus(Status.IN_PROGRESS)).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/usersTournament").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(tournamentStatisticModel)));
    }


}