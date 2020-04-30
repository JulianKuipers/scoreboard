package com.juliankuipers.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.json.JSONArray;

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

    public void login() {

    }

    public void loadRecentGames() {
        JSONArray array = null;
        recentGames.getChildren().clear();

    }
}
