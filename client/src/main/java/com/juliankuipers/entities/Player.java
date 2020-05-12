package com.juliankuipers.entities;

import com.juliankuipers.communication.PlayerCommunication;
import com.juliankuipers.communication.ServerCommunication;
import com.juliankuipers.views.MakeAlert;

import java.io.IOException;

public class Player {

    private int id;
    private String nickname;
    private boolean existingInDatabase;

    /**
     * This is the automatic constructor of this class. Based on the ID it gets the data from the database.
     * @param id The id of the player.
     */
    public Player(int id) {
        this.existingInDatabase = false;
        this.id = id;
        this.nickname = null;
        try {
            this.nickname = PlayerCommunication.getPlayerById(id).getString("nickname");
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isExistingInDatabase() {
        return existingInDatabase;
    }

    public void setExistingInDatabase(boolean existingInDatabase) {
        this.existingInDatabase = existingInDatabase;
    }
}
