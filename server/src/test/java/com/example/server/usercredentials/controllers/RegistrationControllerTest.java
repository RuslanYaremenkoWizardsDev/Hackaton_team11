package com.example.server.usercredentials.controllers;

import com.example.server.usercredentials.exception.UserAlreadyExist;
import com.example.server.usercredentials.exception.conrolleradvice.UserCredentialsControllerAdvice;
import com.example.server.usercredentials.model.dto.UserCredentials;
import com.example.server.usercredentials.repo.UserRepository;
import com.example.server.usercredentials.services.impl.RegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import java.util.stream.Stream;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.*;
import static com.example.server.usercredentials.utils.constants.Mappings.REGISTRATION_MAPPING;
import static com.example.server.usercredentials.utils.constants.Responses.SUCCESS;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RegistrationControllerTest {
    private static final String loginJeid = "jeid";
    private static final String passwordJeid = "123456";
    private static final String emailJeid = "wewrer@ere.ua";
    private static final String secretKeyJeid = "qwert";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final BindingResult bindingResult = Mockito.mock(BindingResult.class);
    private final RegistrationService registrationService = Mockito.mock(RegistrationService.class);
    private final RegistrationController cut = new RegistrationController(userRepository, registrationService);
    private MockMvc mockMvc;

    private static Stream<Arguments> validDataSource() {
        return Stream.of(
                Arguments.arguments(new UserCredentials(loginJeid, passwordJeid, emailJeid, secretKeyJeid)),
                Arguments.arguments(new UserCredentials("Juliya1", "qwe2rty", "swew@ssxmail.com", "Qwewdas")),
                Arguments.arguments(new UserCredentials("VLAD11", "123321", "swew@ssmpail.com", "Qwewwdas"))
        );
    }

    private static Stream<Arguments> emptyPasswordSource() {
        return Stream.of(
                Arguments.arguments(new UserCredentials(loginJeid, null, "swew@sssaiol.com", "Qwewdeas")),
                Arguments.arguments(new UserCredentials("Juliya2", null, "swewssaaud@mail.com", "Qwerwdas"))
        );
    }

    private static Stream<Arguments> userExistSource() {
        return Stream.of(
                Arguments.arguments(new UserCredentials("VLA3D", "jfii23dfi3", "swasdetw@mail.com", "Qwetwdas")),
                Arguments.arguments(new UserCredentials(loginJeid, "qwerty", "swewasda@meail.com", "Qweywdas")),
                Arguments.arguments(new UserCredentials("qwertyuio", passwordJeid, "swedasdw@xamail.com", "Qwewduas"))
        );
    }

    private static Stream<Arguments> emptyLoginSource() {
        return Stream.of(
                Arguments.arguments(new UserCredentials(null, "jfii2dfi3", "sadsdwexzcw@mail.com", "Qwewidas")),
                Arguments.arguments(new UserCredentials(null, "qwer22ty", "swasdaaxczew@mail.com", "Qwewodas"))
        );
    }

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(cut, "registrationService", registrationService);
        mockMvc = MockMvcBuilders.standaloneSetup(cut)
                .setControllerAdvice(UserCredentialsControllerAdvice.class)
                .build();
    }

    @ParameterizedTest
    @MethodSource("userExistSource")
    public void controllerUserExistExceptionTest(UserCredentials userCredentials) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Exception exception = new UserAlreadyExist(USER_WITH_SAME_CREDENTIALS_EXIST);
        Mockito.doThrow(exception).when(registrationService).saveUserToDb(userCredentials);
        mockMvc.perform(post(REGISTRATION_MAPPING).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(userCredentials)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(exception.getMessage()));
    }

    @ParameterizedTest
    @MethodSource("validDataSource")
    public void controllerValidTest(UserCredentials userCredentials) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        mockMvc.perform(post(REGISTRATION_MAPPING).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(userCredentials)))
                .andExpect(status().isOk())
                .andExpect(content().string(SUCCESS));
    }

    @ParameterizedTest
    @MethodSource("emptyPasswordSource")
    public void controllerEmptyPasswordExceptionTest(UserCredentials userCredentials) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        mockMvc.perform(post(REGISTRATION_MAPPING).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(userCredentials)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(EMPTY_FIELD));
    }

    @Test
    public void controllerNullExceptionTest() throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        mockMvc.perform(post(REGISTRATION_MAPPING).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(null)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""));
    }

    @ParameterizedTest
    @MethodSource("emptyLoginSource")
    public void controllerEmptyLoginExceptionTest(UserCredentials userCredentials) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        mockMvc.perform(post(REGISTRATION_MAPPING).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(userCredentials)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(EMPTY_FIELD));
    }
}