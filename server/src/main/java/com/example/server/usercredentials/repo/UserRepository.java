package com.example.server.usercredentials.repo;

import com.example.server.usercredentials.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Person,Long> {
}
