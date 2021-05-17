package com.example.server.tournament.services;

import com.example.server.tournament.model.entity.TournamentEntity;
import com.example.server.tournament.model.enums.Level;
import com.example.server.tournament.model.enums.Mode;
import com.example.server.tournament.model.enums.ScenatioOfTournament;
import com.example.server.tournament.model.enums.Status;
import com.example.server.tournament.repo.TournamentRepo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class CheckStartTimeTournamentTest {
    private final TournamentRepo tournamentRepo = Mockito.mock(TournamentRepo.class);
    private final CheckStartTimeTournament cut = new CheckStartTimeTournament(tournamentRepo);

    private static Stream<Arguments> runTestSource() {
        List<TournamentEntity> list1 = new ArrayList<TournamentEntity>();
        list1.add(new TournamentEntity(1L, Status.IN_PROGRESS, "fff", "f2ff", Mode.CUP, "fff3a", 123123123123L, 1231231235345L, Level.MIDDLE, 32, ScenatioOfTournament.ONE_MATCH));
        List<TournamentEntity> list2 = new ArrayList<TournamentEntity>();
        list2.add(new TournamentEntity(2L, Status.IN_PROGRESS, "fdff", "ffftty", Mode.CUP, "fffrra", 123123123122L, 1231231235342L, Level.MIDDLE, 64, ScenatioOfTournament.ONE_MATCH));
        return Stream.of(
                Arguments.arguments(list1),
                Arguments.arguments(list2)
        );
    }

    @ParameterizedTest
    @MethodSource("runTestSource")
    void runTest(List<TournamentEntity> tournamentEntities) {
        Mockito.when(tournamentRepo.findAllByStatus(Status.IN_PROGRESS)).thenReturn(tournamentEntities);
        Mockito.when(tournamentRepo.save(tournamentEntities.get(0))).thenReturn(tournamentEntities.get(0));

        cut.run();

        Mockito.verify(tournamentRepo, Mockito.times(1)).save(tournamentEntities.get(0));
    }
}