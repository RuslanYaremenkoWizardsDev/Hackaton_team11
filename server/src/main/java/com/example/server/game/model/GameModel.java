package com.example.server.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import static com.example.server.game.util.Constants.TABLE_RESULT_GAME;

@Entity
@Table(name = TABLE_RESULT_GAME)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idTournament;
    private Long finishTime;
    private Long idWinnerCUP;
}
