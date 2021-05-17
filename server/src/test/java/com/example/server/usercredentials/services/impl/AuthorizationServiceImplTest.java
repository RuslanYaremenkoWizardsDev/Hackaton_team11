package com.example.server.usercredentials.services.impl;

import com.example.server.usercredentials.exception.IncorrectPasswordException;
import com.example.server.usercredentials.exception.UserNotFoundException;
import com.example.server.usercredentials.model.dto.AuthorizationDto;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.stream.Stream;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthorizationServiceImplTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
    private final AuthorizationServiceImpl cut = new AuthorizationServiceImpl(userRepository, bCryptPasswordEncoder);

    private static Stream<Arguments> authorizeUserTestSource() {
        return Stream.of(
                Arguments.arguments(new AuthorizationDto("Petr", "paswwerqsw")),
                Arguments.arguments(new AuthorizationDto("griwersha", "paswresww2")),
                Arguments.arguments(new AuthorizationDto("pewers", "passwwrq123"))
        );
    }

    private static Stream<Arguments> authorizeUserEmailTestSource() {
        return Stream.of(
                Arguments.arguments(new AuthorizationDto("Pewwt@r", "paswqtwsw")),
                Arguments.arguments(new AuthorizationDto("gri@resha", "pastwsww2")),
                Arguments.arguments(new AuthorizationDto("pewes@", "passwrwq123"))
        );
    }

    private static Stream<Arguments> authorizeUserNotFoundTestSource() {
        return Stream.of(
                Arguments.arguments(new AuthorizationDto("Petw@r", "paswewqsw")),
                Arguments.arguments(new AuthorizationDto("graiar@sha", "paseweww2")),
                Arguments.arguments(new AuthorizationDto("peeasdas@", "passwweeq123"))
        );
    }

    private static Stream<Arguments> authorizeUserIncorrectPassTestSource() {
        return Stream.of(
                Arguments.arguments(new AuthorizationDto("Peta@r", "paswaqsw")),
                Arguments.arguments(new AuthorizationDto("grasad@sha", "pasasww2")),
                Arguments.arguments(new AuthorizationDto("peasdas@", "passwaq123"))
        );
    }

    @ParameterizedTest
    @MethodSource("authorizeUserTestSource")
    void authorizeUserTest(AuthorizationDto authorizationDto) {
        Person expected = new Person(authorizationDto.getLogin(), "emaadseeil@com.fd", "2$10$daafnwejhfni2edamiid3", "2$10$daaewfnjh21fni2edamewqdssiid3");
        Mockito.when(userRepository.findByLogin(authorizationDto.getLogin())).thenReturn(expected);
        Mockito.when(bCryptPasswordEncoder.matches(authorizationDto.getPassword(), expected.getPassword())).thenReturn(true);

        Person actual = cut.authorizeUser(authorizationDto);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("authorizeUserEmailTestSource")
    void authorizeUserEmailTest(AuthorizationDto authorizationDto) {
        Person expected = new Person("Gregessorii", authorizationDto.getLogin(), "2$10$daafeenjhfni2edamiid3", "2$10$daafnjhew21fni2edamewqdssiid3");
        Mockito.when(userRepository.findByEmail(authorizationDto.getLogin())).thenReturn(expected);
        Mockito.when(bCryptPasswordEncoder.matches(authorizationDto.getPassword(), expected.getPassword())).thenReturn(true);

        Person actual = cut.authorizeUser(authorizationDto);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("authorizeUserNotFoundTestSource")
    void authorizeUserNotFoundTest(AuthorizationDto authorizationDto) {
        Mockito.when(userRepository.findByEmail(authorizationDto.getLogin())).thenReturn(null);

        Throwable thrown = assertThrows(UserNotFoundException.class, () -> {
            cut.authorizeUser(authorizationDto);
        });
        assertEquals(String.format(USER_NOT_FOUND, authorizationDto.getLogin()), thrown.getMessage());
    }

    @ParameterizedTest
    @MethodSource("authorizeUserIncorrectPassTestSource")
    void authorizeUserIncorrectPassTest(AuthorizationDto authorizationDto) {
        Person expected = new Person("Greeegorii", authorizationDto.getLogin(), "2$10$daafnjheefni2edamiid3", "2$10$daafnjh21fniee2edamewqdssiid3");
        Mockito.when(userRepository.findByEmail(authorizationDto.getLogin())).thenReturn(expected);
        Mockito.when(bCryptPasswordEncoder.matches(authorizationDto.getPassword(), expected.getPassword())).thenReturn(false);

        Throwable thrown = assertThrows(IncorrectPasswordException.class, () -> {
            cut.authorizeUser(authorizationDto);
        });
        assertEquals(INCORRECT_PASSWORD, thrown.getMessage());
    }
}