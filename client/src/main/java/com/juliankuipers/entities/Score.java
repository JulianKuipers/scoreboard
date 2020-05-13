package com.juliankuipers.entities;

import com.juliankuipers.communication.ScoreCommunication;
import com.juliankuipers.views.MakeAlert;
import org.json.JSONObject;

import java.io.IOException;

public class Score {

    private int id;
    private Player player;
    private Game game;
    private int score;
    private boolean existingInDatabase;

    /**
     * This is the automatic constructor of this class. It will fetch the data from the database according to the id.
     * @param id The id to use.
     */
    public Score(int id) {
        this.existingInDatabase = false;
        this.id = id;
        this.player = null;
        this.game = null;
        this.score = -1;
        JSONObject data;
        try {
            data = ScoreCommunication.getScoreById(id);
            this.game = new Game(data.getJSONObject("game").getInt("id"));
            this.player = new Player(data.getJSONObject("player").getInt("id"));
            this.score = data.getInt("score");
            this.existingInDatabase = true;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            MakeAlert.serverCommunicationFailed();
        }
    }

    public Score(int id, Game game, Player player) {
        this.existingInDatabase = false;
        this.id = id;
        this.player = player;
        this.game = game;
        this.score = -1;
        JSONObject data;
        try {
            data = ScoreCommunication.getScoreById(id);
            this.score = data.getInt("score");
            this.existingInDatabase = true;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            MakeAlert.serverCommunicationFailed();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isExistingInDatabase() {
        return existingInDatabase;
    }

    public void setExistingInDatabase(boolean existingInDatabase) {
        this.existingInDatabase = existingInDatabase;
    }
}
