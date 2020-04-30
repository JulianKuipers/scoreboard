package com.juliankuipers.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class LoginDisplay extends Application {

    public static Stage parentWindow;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/login.fxml");
        loader.setLocation(xmlUrl);

        parentWindow = primaryStage;
        Parent root = loader.load();

        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1000);

        parentWindow.setScene(new Scene(root));
        parentWindow.setResizable(true);

        primaryStage.setTitle("Scoreboard - Desktop Client");
        primaryStage.show();
    }
}
