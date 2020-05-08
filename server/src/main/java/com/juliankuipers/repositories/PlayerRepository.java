package com.juliankuipers.repositories;

import com.juliankuipers.entities.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    Player findByNickname(String nickname);

}