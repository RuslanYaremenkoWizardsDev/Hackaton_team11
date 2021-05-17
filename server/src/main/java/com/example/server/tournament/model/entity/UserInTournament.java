package com.example.server.tournament.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import static com.example.server.tournament.util.Constants.TABLE_USERS_TOURNAMENT;

@Entity
@Table(name = TABLE_USERS_TOURNAMENT)
@Data
@NoArgsConstructor
public class UserInTournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idTournament;
    private Long idUser;

    public UserInTournament(long idTournament, long idUser) {
        this.idTournament = idTournament;
        this.idUser = idUser;
    }
}
