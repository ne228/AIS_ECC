package com.example.ais_ecc.repositories;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import com.example.ais_ecc.entity.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository
        extends JpaRepository<User, String> {


    List<User> findAllBy();
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}