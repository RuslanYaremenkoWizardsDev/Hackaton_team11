package com.example.server.usercredentials.controllers;

import com.example.server.usercredentials.exception.UserAlreadyExist;
import com.example.server.usercredentials.exception.conrolleradvice.UserCredentialsControllerAdvice;
import com.example.server.usercredentials.model.dto.AuthorizationDto;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.services.impl.AuthorizationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import java.util.stream.Stream;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.USER_WITH_SAME_CREDENTIALS_EXIST;
import static com.example.server.usercredentials.utils.constants.Mappings.AUTHORIZATION_MAPPING;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthorizationControllerTest {
    private final AuthorizationServiceImpl authorizationService = Mockito.mock(AuthorizationServiceImpl.class);
    private final BindingResult bindingResult = Mockito.mock(BindingResult.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AuthorizationController cut = new AuthorizationController(authorizationService);
    private MockMvc mockMvc;


    private static Stream<Arguments> authorizeUserTestSource() {
        return Stream.of(
                Arguments.arguments(new AuthorizationDto("Petr", "paswqsw")),
                Arguments.arguments(new AuthorizationDto("grisha", "passww2")),
                Arguments.arguments(new AuthorizationDto("pes", "passwq123"))
        );
    }

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(cut, "authorizationService", authorizationService);
        mockMvc = MockMvcBuilders.standaloneSetup(cut)
                .setControllerAdvice(UserCredentialsControllerAdvice.class)
                .build();
    }

    @ParameterizedTest
    @MethodSource("authorizeUserTestSource")
    public void controllerValidTest(AuthorizationDto authorizationDto) throws Exception {
        Person expected = new Person(authorizationDto.getLogin(), "saba@dfdfs.dd", "sadasda", "sssss");
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(authorizationService.authorizeUser(authorizationDto)).thenReturn(expected);
        mockMvc.perform(post(AUTHORIZATION_MAPPING).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(authorizationDto)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(expected)));
    }


//    @ParameterizedTest
//    @MethodSource("userExistSource")
    public void controllerUserExistExceptionTest(AuthorizationDto authorizationDto) throws Exception {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Exception exception = new UserAlreadyExist(USER_WITH_SAME_CREDENTIALS_EXIST);
        Mockito.doThrow(exception).when(authorizationService).authorizeUser(authorizationDto);
        mockMvc.perform(post(AUTHORIZATION_MAPPING).contentType(APPLICATION_JSON).content(objectMapper
                .writeValueAsString(authorizationDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(exception.getMessage()));
    }
}