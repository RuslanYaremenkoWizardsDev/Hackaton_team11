package com.example.server.usercredentials.repo;

import com.example.server.usercredentials.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<Person,Long> {
    Person findByLogin(String login);
    Person findByEmail(String email);
}
