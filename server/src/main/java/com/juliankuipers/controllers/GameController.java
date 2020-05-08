package com.juliankuipers.controllers;

import com.juliankuipers.entities.Game;
import com.juliankuipers.repositories.GameRepository;
import com.juliankuipers.repositories.GameRepositoryImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameRepositoryImplementation gameRepositoryImplementation;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewGame (@RequestParam String name,
                       @RequestParam String description) {
        Game g = new Game();
        g.setName(name);
        g.setDescription(description);
        gameRepository.save(g);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping(path = "/recent/{number}")
    public @ResponseBody Iterable<Game> getRecentGames(@PathVariable int number) {
        return gameRepositoryImplementation.findTop(number);
    }

    // Change to proper url formats with name:value queries
    @GetMapping(path = "/name/{name}")
    public @ResponseBody Game getGameByName(@PathVariable String name) {
        return gameRepository.findByName(name);
    }

    @GetMapping(path = "/id/{id}")
    public @ResponseBody Game getGameById(@PathVariable int id) {
        return gameRepository.findById(id);
    }
}
