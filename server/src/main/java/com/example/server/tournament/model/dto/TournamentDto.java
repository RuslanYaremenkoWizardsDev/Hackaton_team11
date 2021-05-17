package com.example.server.tournament.model.dto;

import com.example.server.tournament.model.enums.Level;
import com.example.server.tournament.model.enums.Mode;
import com.example.server.tournament.model.enums.ScenatioOfTournament;
import com.example.server.tournament.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import static com.example.server.tournament.util.Constants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TournamentDto implements Serializable {
    private Long id;
    private Status status;
    @NotBlank(message = ERROR_NAME)
    @Size(max = 255)
    private String name;
    @NotBlank(message = ERROR_DESCRIPTION)
    @Size(max = 10000)
    private String tournamentDescription;
    private Mode modeTournament;
    @NotBlank(message = ERROR_PLACE)
    private String place;
    @NotNull(message = ERROR_DATE_START_TOURNAMENT)
    private Long dateStartTournament;
    @NotNull(message = ERROR_DATE_LAST_REGISTRATION)
    private Long dateLastRegistrationOnTournament;
    private Level level;
    private int numberOfPlayer;
    private ScenatioOfTournament scenarioOfTournament;
}
