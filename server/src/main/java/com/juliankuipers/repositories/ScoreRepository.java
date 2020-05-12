package com.juliankuipers.repositories;

import com.juliankuipers.entities.Player;
import com.juliankuipers.entities.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ScoreRepository extends CrudRepository<Score, Integer> {

    Score findByPlayer(Player player);

    @Query(value = "SELECT DISTINCT * FROM scores AS s WHERE s.game_id = :id", nativeQuery = true)
    Set<Score> findAllByGameId(@Param("id") int id);

    @Query(value = "SELECT DISTINCT * FROM scores AS s WHERE s.game_id = :id ORDER BY s.score DESC", nativeQuery = true)
    Set<Score> findAllByGameIdRanked(@Param("id") int id);

    Iterable<Score> findAllByPlayer(Player byNickname);

}