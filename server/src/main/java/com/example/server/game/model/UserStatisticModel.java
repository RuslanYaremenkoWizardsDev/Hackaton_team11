package com.example.server.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "user_statistic")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserStatisticModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idUser;
    private Long wins;
    private Long lose;
    private Long draws;
    private Long winsCup;
}
