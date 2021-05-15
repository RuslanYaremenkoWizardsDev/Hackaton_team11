package com.example.server.tournament.model;

import com.example.server.tournament.model.enums.Level;
import com.example.server.tournament.model.enums.Mode;
import com.example.server.tournament.model.enums.ScenatioOfTournament;
import com.example.server.tournament.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "all_tournament")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDTO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Status status = Status.IN_PROGRESS;
    private String name;
    private String tournamentDescription;
    private Mode modeTournament;
    private String place;
    private long dateStartTournament;
    private long dateLastRegistrationOnTournament;
    private Level level = Level.MIDDLE;
    private int numberOfPlayer = 32;
    private ScenatioOfTournament scenatioOfTournament = ScenatioOfTournament.ONE_MATCH;

}
