package com.example.server.tournament.controller;

import com.example.server.tournament.exception.controlleradvice.TournamentControllerAdvice;
import com.example.server.tournament.model.dto.UserDtoForTournament;
import com.example.server.tournament.pool.Pool;
import com.example.server.tournament.services.AddUserToTournament;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import java.util.ArrayList;
import java.util.stream.Stream;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.EMPTY_FIELD;
import static com.example.server.usercredentials.utils.constants.Mappings.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegisterUserControllerTest {
    private final AddUserToTournament addUserToTournament = Mockito.mock(AddUserToTournament.class);
    private final Pool pool = Mockito.mock(Pool.class);
    private final BindingResult bindingResult = Mockito.mock(BindingResult.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RegisterUserController cut = new RegisterUserController(addUserToTournament, pool);
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(cut)
                .setControllerAdvice(TournamentControllerAdvice.class)
                .build();
    }

    @Test
    public void testAddInvite() throws Exception {
        UserDtoForTournament userDtoForTournament = new UserDtoForTournament();
        Mockito.doNothing().when(pool).addInvite(userDtoForTournament);
        mockMvc.perform(post(GET_INVITE))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void getALlInvites() throws Exception {
        ArrayList<UserDtoForTournament> userDtoForTournament = new ArrayList<>();
        Mockito.when(pool.getAllInvites()).thenReturn(userDtoForTournament);
        mockMvc.perform(post(GET_ALL_INVITE))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(userDtoForTournament)));
    }

    @Test
    public void addInvite() throws Exception {
        UserDtoForTournament userDtoForTournament = new UserDtoForTournament("login1", "passwo2rd");
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.doNothing().when(pool).addInvite(userDtoForTournament);
        mockMvc.perform(post(ADD_INVITE).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(userDtoForTournament)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }

    private static Stream<Arguments> addInvaidInvalidSource() {
        return Stream.of(
                Arguments.arguments(new UserDtoForTournament("sddf", null)),
                Arguments.arguments(new UserDtoForTournament("fdd", "")),
                Arguments.arguments(new UserDtoForTournament(null, "passwford"))
        );
    }

    @ParameterizedTest
    @MethodSource("addInvaidInvalidSource")
    public void addInviteException(UserDtoForTournament userDtoForTournament) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        mockMvc.perform(post(ADD_INVITE).contentType(MediaType.APPLICATION_JSON).content(objectMapper
                .writeValueAsString(userDtoForTournament)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(EMPTY_FIELD));
    }

    private static Stream<Arguments> registerInTournamentValidSource() {
        return Stream.of(
                Arguments.arguments(new UserDtoForTournament("sdfdf", "null")),
                Arguments.arguments(new UserDtoForTournament("fdsd", "dfsdf")),
                Arguments.arguments(new UserDtoForTournament("dfdfsf", "passwodrd"))
        );
    }

    @ParameterizedTest
    @MethodSource("registerInTournamentValidSource")
    public void registrationInTournamentValid(UserDtoForTournament userDtoForTournament) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        mockMvc.perform(post(REGISTER_USER_IN_TOURNAMENT).contentType(MediaType.APPLICATION_JSON).content(objectMapper
                .writeValueAsString(userDtoForTournament)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    private static Stream<Arguments> registerInTournamentInvalidSource() {
        return Stream.of(
                Arguments.arguments(new UserDtoForTournament("sdf", null)),
                Arguments.arguments(new UserDtoForTournament("ffdd", "")),
                Arguments.arguments(new UserDtoForTournament(null, "password"))
        );
    }

    @ParameterizedTest
    @MethodSource("registerInTournamentInvalidSource")
    public void registrationInTournamentInValid(UserDtoForTournament userDtoForTournament) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        mockMvc.perform(post(REGISTER_USER_IN_TOURNAMENT).contentType(MediaType.APPLICATION_JSON).content(objectMapper
                .writeValueAsString(userDtoForTournament)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(EMPTY_FIELD));
    }

}