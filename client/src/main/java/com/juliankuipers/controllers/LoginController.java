package com.juliankuipers.controllers;

import com.juliankuipers.communication.ServerCommunication;
import com.juliankuipers.views.MakeAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public PasswordField loginPassword;
    public TextField loginEmail;
    public Button loginButton;
    public VBox recentGames;
    @FXML
    private BorderPane window;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    loadRecentGames();
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
        seeGameButton.setOnAction(event -> loadGame(game.getInt("id")));
        gamePane.setRight(new HBox(seeGameButton));
        recentGames.getChildren().add(gamePane);
    }

    /**
     * Pass the id to the next scene and load that scene.
     *
     * @param gameId the id of the game that is selected.
     */
    @FXML
    private void loadGame(int gameId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game.fxml"));
            Parent root = loader.load();
            GameController gameController = loader.getController();
            gameController.setData(gameId);
            Stage windowStage = (Stage) window.getScene().getWindow();
            windowStage.getScene().setRoot(root);
            windowStage.sizeToScene();
            windowStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            MakeAlert.error("Something went wrong!", "Oops, please try again.");
        }
    }
}
