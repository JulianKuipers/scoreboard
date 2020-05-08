package com.juliankuipers.repositories;

import com.juliankuipers.entities.Game;
import com.juliankuipers.entities.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.sql.Date;

public interface GameRepository extends CrudRepository<Game, Integer> {

    Game findByName(String name);

    Game findById(int id);

    @Query("SELECT g FROM Game g WHERE g.date = :date")
    Iterable<Game> findAllByDate(@Param("date") Date date);

    @Query("SELECT g FROM Game g ORDER BY g.id asc")
    Iterable<Game> findAllAsc();

}