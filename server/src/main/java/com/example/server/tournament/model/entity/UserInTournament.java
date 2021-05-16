package com.example.server.tournament.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "users_tournament")
@Data
@NoArgsConstructor
public class UserInTournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idTournament;
    private Long idUser;

    public UserInTournament(long id_tournament, long id_user) {
        this.idTournament = id_tournament;
        this.idUser = id_user;
    }
}
