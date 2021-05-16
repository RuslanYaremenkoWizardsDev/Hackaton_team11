package com.example.server.tournament.model.entity;

import com.example.server.tournament.model.enums.Level;
import com.example.server.tournament.model.enums.Mode;
import com.example.server.tournament.model.enums.ScenatioOfTournament;
import com.example.server.tournament.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentEntity implements Serializable {
    private Long id;
    private Status status ;
    @NotBlank(message = "error name")
    @Size(max = 255)
    private String name;
    @NotBlank(message = "error description")
    @Size(max = 1000)
    private String tournamentDescription;
    private Mode modeTournament;
    @NotBlank(message = "error place")
    private String place;
    @NotNull(message = "error dateStartTournament")
    private Long dateStartTournament;
    @NotNull(message = "error dateLastRegistrationOnTournament")
    private Long dateLastRegistrationOnTournament;
    private Level level ;
    private int numberOfPlayer;
    private ScenatioOfTournament scenatioOfTournament ;
}
