package com.juliankuipers.entities;

import com.juliankuipers.communication.GameCommunication;
import com.juliankuipers.views.MakeAlert;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Game {

    // Private fields, attributes of this class
    private int id;
    private String name;
    private String description;
    private Date date;
    private Set<Score> rankedScores;
    private Set<Player> players;
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
        this.rankedScores = new HashSet<Score>();
        this.players = new HashSet<Player>();
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
        this.rankedScores = new HashSet<Score>();
        this.players = new HashSet<Player>();
        JSONObject data;
        try {
            data = GameCommunication.getAllGameData(this.id);
            System.out.println(data);
            this.name = data.getString("name");
            this.description = data.getString("description");
            this.date = Date.valueOf(data.getString("date"));
            JSONArray scoresArray = data.getJSONArray("scores");
            JSONArray playerArray = data.getJSONArray("players");
            for (Object obj : playerArray) {
                System.out.println(obj);
                JSONObject player = new JSONObject(obj);
                System.out.println(player);
                Player newPlayer = new Player(player.getInt("id"));
                this.players.add(newPlayer);
            }
            for (Object obj : scoresArray) {
                JSONObject score = new JSONObject(obj);
                Score newScore = new Score(score.getInt("id"));
                this.rankedScores.add(newScore);
            }
            this.existingInDatabase = true;
        } catch (IOException | InterruptedException e) {
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

    public Set<Score> getRankedScores() {
        return rankedScores;
    }

    public void setRankedScores(Set<Score> rankedScores) {
        this.rankedScores = rankedScores;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public boolean isExistingInDatabase() {
        return existingInDatabase;
    }

    public void setExistingInDatabase(boolean existingInDatabase) {
        this.existingInDatabase = existingInDatabase;
    }
}
