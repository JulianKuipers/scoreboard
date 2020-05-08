package com.juliankuipers.entities;

import com.juliankuipers.communication.GameCommunication;
import com.juliankuipers.communication.RequestMaker;
import com.juliankuipers.views.MakeAlert;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Date;
import java.util.Set;

public class Game {

    // Private fields, attributes of this class
    private int id;
    private String name;
    private String description;
    private Date date;
    private Set<Score> scores;
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
    }

    /**
     * This is the automatic constructor for this class. Given the id of the game, it will try to get all the game details from the server.
     * @param id The id of the game you want to get the details from.
     */
    public Game(int id) {
        this.existingInDatabase = false;
        this.id = id;
        JSONObject data = null;
        try {
            data = GameCommunication.getAllGameData(this.id);
            this.name = data.getString("name");
            this.description = data.getString("description");
            this.date = Date.valueOf(data.getString("date"));
            JSONArray scoresArray = data.getJSONArray("scores");
            JSONArray playerArray = data.getJSONArray("players");
            //Create parsers in Player and Score
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            MakeAlert.serverCommunicationFailed();
        }
    }
}
