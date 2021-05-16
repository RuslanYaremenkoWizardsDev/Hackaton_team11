package com.example.server.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import static com.example.server.game.util.Constants.TABLE_BATTLE_USERS;

@Data
@Entity
@Table(name = TABLE_BATTLE_USERS)
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
