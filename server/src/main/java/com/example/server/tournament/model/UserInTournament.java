package com.example.server.tournament.model;

import com.example.server.usercredentials.model.dto.UserCredentials;
import com.example.server.usercredentials.model.entity.Person;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users_tournament")
@Data
public class UserInTournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long id_tournament;
    private Long id_user;
}
