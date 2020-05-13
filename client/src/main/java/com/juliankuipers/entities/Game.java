package com.juliankuipers.entities;

import com.juliankuipers.communication.GameCommunication;
import com.juliankuipers.exceptions.PlayerNotInArrayException;
import com.juliankuipers.views.MakeAlert;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Game {

    // Private fields, attributes of this class
    private int id;
    private String name;
    private String description;
    private Date date;
    private ArrayList<Score> rankedScores;
    private ArrayList<Player> players;
    private boolean existingInDatabase;

    /**
     * This is the manual constructor for this class.
     * @param name          The name of the game.
     * @param description   The description of the game.
     */
    public Game(String name, String description, Date date) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.existingInDatabase = false;
        this.rankedScores = new ArrayList<Score>();
        this.players = new ArrayList<Player>();
    }

    /**
     * This is the automatic constructor for this class. Given the id of the game, it will try to get all the game details from the server.
     * @param id The id of the game you want to get the details from.
     */
    public Game(int id) {
        this.existingInDatabase = false;
        this.id = id;
        this.date = null;
        this.name = null;
        this.rankedScores = new ArrayList<Score>();
        this.players = new ArrayList<Player>();
        JSONObject data;
        try {
            data = GameCommunication.getAllGameData(this.id);
            this.name = data.getString("name");
            this.description = data.getString("description");
            this.date = Date.valueOf(data.getString("date"));
            JSONArray scoresArray = data.getJSONArray("scores");
            JSONArray playerArray = data.getJSONArray("players");
            for (int i = 0; i < playerArray.length(); i++) {
                Player newPlayer = new Player(playerArray.getJSONObject(i).getInt("id"));
                this.players.add(newPlayer);
            }
            for (int i = 0; i < scoresArray.length(); i++) {
                JSONObject score = scoresArray.getJSONObject(i);
                Score newScore = new Score(score.getInt("id"), this, this.getPlayerById(score.getJSONObject("player").getInt("id")));
                this.rankedScores.add(newScore);
            }
            this.existingInDatabase = true;
        } catch (IOException | InterruptedException | PlayerNotInArrayException e) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Score> getRankedScores() {
        return rankedScores;
    }

    public void setRankedScores(ArrayList<Score> rankedScores) {
        this.rankedScores = rankedScores;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean isExistingInDatabase() {
        return existingInDatabase;
    }

    public void setExistingInDatabase(boolean existingInDatabase) {
        this.existingInDatabase = existingInDatabase;
    }
    
    private Player getPlayerById(int id) throws PlayerNotInArrayException {
        Player player = null;
        System.out.println(id);
        for (Player value : this.players) {
            if (value.getId() == id) {
                player = value;
            }
        }
        if (player == null) {
            throw new PlayerNotInArrayException();
        }
        return player;
    }
}
