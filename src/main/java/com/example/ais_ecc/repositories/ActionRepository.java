package com.example.ais_ecc.repositories;


import com.example.ais_ecc.entity.actions.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository  extends CrudRepository<Action, String> {


}
