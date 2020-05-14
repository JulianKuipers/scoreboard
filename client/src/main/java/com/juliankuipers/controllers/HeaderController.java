package com.juliankuipers.controllers;

import com.juliankuipers.views.MakeAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class HeaderController {
    @FXML
    private Button backHome;


    /**
     * Signs the user out.
     */
    public void logout() {
    }

    /**
     * Takes the user back home.
     */
    @FXML
    private void backHome() throws IOException {
        Stage window = (Stage) backHome.getScene().getWindow();
        loadScene(window);
    }

    private void loadScene(Stage window) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        window.getScene().setRoot(root);
        window.sizeToScene();
        window.show();
    }
}
