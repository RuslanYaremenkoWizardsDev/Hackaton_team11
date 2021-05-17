package com.example.server.usercredentials.services.impl;

import com.example.server.usercredentials.model.dto.ForgotPassDto;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ForgotPasswordServicesTest {
    private final UserRepository userRepo = Mockito.mock(UserRepository.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
    private final ForgotPasswordServices cut = new ForgotPasswordServices(userRepo, bCryptPasswordEncoder);

    private static Stream<Arguments> updatePasswordTestSource() {
        return Stream.of(
                Arguments.arguments(new ForgotPassDto("login1", "secret1", "password1")),
                Arguments.arguments(new ForgotPassDto("login2", "secret2", "password2")),
                Arguments.arguments(new ForgotPassDto("login3", "secret3", "password3"))
        );
    }
    private static Stream<Arguments> updatePasswordExceptionTestSource() {
        return Stream.of(
                Arguments.arguments(new ForgotPassDto("login12", "secret12", "password12")),
                Arguments.arguments(new ForgotPassDto("login23", "secret23", "password23")),
                Arguments.arguments(new ForgotPassDto("login34", "secret34", "password34"))
        );
    }

    @ParameterizedTest
    @MethodSource("updatePasswordTestSource")
    void updatePasswordTest(ForgotPassDto forgotPassDto) {
        String secretEncode = "2$10$sdwe214fs4336wfag4364";
        Person testPerson = new Person(forgotPassDto.getLogin(), "emai@l.com", "OLDPASSS", secretEncode);
        Mockito.when(userRepo.findByLogin(forgotPassDto.getLogin())).thenReturn(testPerson);
        Mockito.when(bCryptPasswordEncoder.matches(forgotPassDto.getSecretKey(), testPerson.getSecretKey())).thenReturn(true);
        Mockito.when(userRepo.save(testPerson)).thenReturn(testPerson);

        cut.updatePassword(forgotPassDto);

        Mockito.verify(userRepo, Mockito.times(1)).save(testPerson);
    }

    @ParameterizedTest
    @MethodSource("updatePasswordExceptionTestSource")
    void updatePasswordExceptionTest(ForgotPassDto forgotPassDto) {
        String secretEncode = "2$10$sdwe214fs4336wfag436";
        Person testPerson = new Person(forgotPassDto.getLogin(), "emai@l.co", "OLDPASS", secretEncode);
        Mockito.when(userRepo.findByLogin(forgotPassDto.getLogin())).thenReturn(null);
        Mockito.when(bCryptPasswordEncoder.matches(forgotPassDto.getSecretKey(), testPerson.getSecretKey())).thenReturn(false);
        Mockito.when(userRepo.save(testPerson)).thenReturn(testPerson);

        Throwable thrown = assertThrows(UsernameNotFoundException.class, () -> {
            cut.updatePassword(forgotPassDto);
        });
        assertEquals("User not found ", thrown.getMessage());
    }
}