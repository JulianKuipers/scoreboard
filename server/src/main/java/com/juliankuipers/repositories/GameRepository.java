package com.juliankuipers.repositories;

import com.juliankuipers.entities.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

    Game findByName(String name);
}