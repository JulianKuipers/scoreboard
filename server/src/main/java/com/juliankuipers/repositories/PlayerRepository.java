package com.juliankuipers.repositories;

import com.juliankuipers.entities.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    Player findByNickname(String nickname);
}