package com.juliankuipers.controllers;

import com.juliankuipers.entities.Player;
import com.juliankuipers.entities.User;
import com.juliankuipers.repositories.PlayerRepository;
import com.juliankuipers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/player")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewPlayer (@RequestParam String nickname) {
        Player p = new Player();
        p.setNickname(nickname);
        playerRepository.save(p);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @GetMapping(path="/{nickname}")
    public @ResponseBody Player getPlayerByNickname(@PathVariable String nickname) {
        return playerRepository.findByNickname(nickname);
    }
}
