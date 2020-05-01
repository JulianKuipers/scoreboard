package com.juliankuipers.controllers;

import com.juliankuipers.communication.ServerCommunication;
import com.juliankuipers.views.MakeAlert;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public PasswordField loginPassword;
    public TextField loginEmail;
    public Button loginButton;
    public VBox recentGames;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    loadRecentGames();
    }

    private void loadSceneAndSendMessage(int id) {
    }

    public void login() {

    }

    public void loadRecentGames() {
        JSONArray array = new JSONArray();
        try {
            array = ServerCommunication.getRecentGames();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            MakeAlert.serverCommunicationFailed();
        }
        recentGames.getChildren().clear();
        for (int i = 0; i < array.length(); i++) {
            addGame(array.getJSONObject(i));
        }
    }

    private void addGame(JSONObject game) {
        BorderPane gamePane = new BorderPane();
        String name = game.getString("name");
        Label nameLabel = new Label(name);
        gamePane.setCenter(new HBox(nameLabel));
        Button seeGameButton = new Button("Go to game");
        int id = game.getInt("id");
        seeGameButton.setOnAction(event -> loadSceneAndSendMessage(id));
        gamePane.setRight(new HBox(seeGameButton));
        recentGames.getChildren().add(gamePane);
    }


}
