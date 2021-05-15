package com.example.server.usercredentials.model.entity;
import com.example.server.usercredentials.model.Roles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "user_credentials")
@Data
@NoArgsConstructor
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String login;
    private  String email;
    private  String password;
    private  String secretKey;
    private  String avatar;
    private Roles role;

    public Person(String login, String email, String password, String secretKey) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.secretKey = secretKey;
        this.avatar = null;
        this.role = Roles.USER;
    }
}
