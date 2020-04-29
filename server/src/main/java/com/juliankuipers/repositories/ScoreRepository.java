package com.juliankuipers.repositories;

import com.juliankuipers.entities.Player;
import com.juliankuipers.entities.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Integer> {

    Score findByPlayer(Player Player);
}