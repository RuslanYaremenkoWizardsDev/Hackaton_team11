package com.example.server.usercredentials.repo;

import com.example.server.usercredentials.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends JpaRepository<Person,Long> {
    Person findAllByLogin(String login);
}
