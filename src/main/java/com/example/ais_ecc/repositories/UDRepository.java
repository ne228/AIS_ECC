package com.example.ais_ecc.repositories;

import com.example.ais_ecc.entity.Role;
import com.example.ais_ecc.entity.UD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UDRepository  extends JpaRepository<UD, String> {
}
