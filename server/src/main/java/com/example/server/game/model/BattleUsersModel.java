package com.example.server.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Table(name = "batle_users")
@AllArgsConstructor
@NoArgsConstructor
public class BattleUsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idTournament;
    private String firstLogin;
    private String secondLogin;
    private String winner;
}
