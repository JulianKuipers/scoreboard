package com.juliankuipers.controllers;

import com.juliankuipers.entities.Game;
import com.juliankuipers.entities.Score;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private int id;
    private Game game;
    private int rankingCounter;
    @FXML
    private VBox scores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.rankingCounter = 1;
    }

    private void makeScene() {
        getDetails();
        addScores();
    }

    public void setData(int id) {
        this.id = id;
        makeScene();
    }

    private void getDetails() {
        this.game = new Game(this.id);
    }

    private void addScores() {
        for (Score score : this.game.getRankedScores()) {
            addScore(score);
        }
    }

    private void addScore(Score score) {
        BorderPane scorePane = new BorderPane();
        String rank = String.valueOf(rankingCounter);
        Label rankLabel = new Label(rank);
        rankingCounter++;
        Label nicknameLabel = new Label(score.getPlayer().getNickname());
        Label scoreLabel = new Label(String.valueOf(score.getScore()));
        scorePane.setCenter(new HBox(rankLabel, nicknameLabel, scoreLabel));
        scores.getChildren().add(scorePane);
    }
}
