package com.juliankuipers.controllers;

import com.juliankuipers.entities.Game;
import com.juliankuipers.entities.Score;

import java.sql.Date;
import java.util.Set;

public class GameController {
    private int id;
    private Game game;

    private void makeScene() {
        getDetails();
    }

    public void setData(int id) {
        this.id = id;
        makeScene();
    }

    private void getDetails() {
        this.game = new Game(this.id);
    }
}
