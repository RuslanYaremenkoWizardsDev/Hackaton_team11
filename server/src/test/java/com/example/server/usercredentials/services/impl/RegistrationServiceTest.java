package com.example.server.usercredentials.services.impl;

import com.example.server.game.model.UserStatisticModel;
import com.example.server.game.repo.UserStatisticRepo;
import com.example.server.usercredentials.exception.UserAlreadyExist;
import com.example.server.usercredentials.model.dto.UserCredentials;
import com.example.server.usercredentials.model.entity.Person;
import com.example.server.usercredentials.repo.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.stream.Stream;
import static com.example.server.usercredentials.utils.constants.ExceptionsMessages.USER_WITH_SAME_CREDENTIALS_EXIST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RegistrationServiceTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
    private final UserStatisticRepo userStatisticRepo = Mockito.mock(UserStatisticRepo.class);
    private final RegistrationService cut = new RegistrationService(userRepository, bCryptPasswordEncoder, userStatisticRepo);

    private static Stream<Arguments> saveUserToDbTestSource() {
        return Stream.of(
                Arguments.arguments(new UserCredentials("Vitay", "Password3", "eewm@ail.l", "Secret1")),
                Arguments.arguments(new UserCredentials("Igor", "Password1", "eeexm@ail.l", "Secret2")),
                Arguments.arguments(new UserCredentials("Sveta", "Password2", "emeex@ail.leeee", "Secret3"))
        );
    }

    @ParameterizedTest
    @MethodSource("saveUserToDbTestSource")
    void saveUserToDbTest(UserCredentials userCredentials) {
        String testPass = "2$10$qwqeaads332ffdse4dw2";
        String secretKey = "2$10$qwqewads332dsfsdfffdse4dw2";
        Person testPerson = new Person(userCredentials.getLogin(), userCredentials.getEmail(), testPass, secretKey);
        UserStatisticModel testStatistic = new UserStatisticModel(null, testPerson.getId(), 0L, 0L, 0L, 0L);
        Mockito.when(userRepository.findByLogin(userCredentials.getLogin())).thenReturn(null);
        Mockito.when(userRepository.findByEmail(userCredentials.getEmail())).thenReturn(null);
        Mockito.when(bCryptPasswordEncoder.encode(userCredentials.getPassword())).thenReturn(testPass);
        Mockito.when(bCryptPasswordEncoder.encode(userCredentials.getSecretKey())).thenReturn(secretKey);
        Mockito.when(userRepository.save(testPerson)).thenReturn(testPerson);
        Mockito.when(userStatisticRepo.save(testStatistic)).thenReturn(testStatistic);

        cut.saveUserToDb(userCredentials);

        Mockito.verify(userRepository, Mockito.times(1)).save(testPerson);
        Mockito.verify(userStatisticRepo, Mockito.times(1)).save(testStatistic);
    }

    @ParameterizedTest
    @MethodSource("saveUserToDbTestSource")
    void checkLoginExistTest(UserCredentials userCredentials) {
        String testPass = "2$10$qwqeqads332ffdse4dw2";
        String secretKey = "2$10$qwqeadrs332dsfsdfffdse4dw2";
        Person testPerson = new Person(userCredentials.getLogin(), userCredentials.getEmail(), testPass, secretKey);
        UserStatisticModel testStatistic = new UserStatisticModel(null, testPerson.getId(), 0L, 0L, 0L, 0L);
        Mockito.when(userRepository.findByLogin(userCredentials.getLogin())).thenReturn(testPerson);
        Mockito.when(userRepository.findByEmail(userCredentials.getEmail())).thenReturn(testPerson);
        Mockito.when(bCryptPasswordEncoder.encode(userCredentials.getPassword())).thenReturn(testPass);
        Mockito.when(bCryptPasswordEncoder.encode(userCredentials.getSecretKey())).thenReturn(secretKey);
        Mockito.when(userRepository.save(testPerson)).thenReturn(testPerson);
        Mockito.when(userStatisticRepo.save(testStatistic)).thenReturn(testStatistic);

        Throwable thrown = assertThrows(UserAlreadyExist.class, () -> {
            cut.saveUserToDb(userCredentials);
        });
        assertEquals(userCredentials.getLogin() + USER_WITH_SAME_CREDENTIALS_EXIST, thrown.getMessage());
    }


}