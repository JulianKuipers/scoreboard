package com.juliankuipers.controllers;

import com.juliankuipers.entities.Score;
import com.juliankuipers.repositories.GameRepository;
import com.juliankuipers.repositories.PlayerRepository;
import com.juliankuipers.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/score")
public class ScoreController {
    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private GameRepository gameRepository;

    @PostMapping(path="/add")
    public @ResponseBody
    String addNewScore (@RequestParam Integer playerId,
                       @RequestParam Integer gameId,
                        @RequestParam int score) {
        Score s = new Score();
        s.setPlayer(playerRepository.findById(playerId).orElse(null));
        s.setGame(gameRepository.findById(gameId).orElse(null));
        s.setScore(score);
        scoreRepository.save(s);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    @GetMapping(path="/{nickname}")
    public @ResponseBody Score getScoreByNickname(@PathVariable String nickname) {
        return scoreRepository.findByPlayer(playerRepository.findByNickname(nickname));
    }
}
