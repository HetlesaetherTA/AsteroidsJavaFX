package com.hetlesaetherta.asteroids;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group(), 800,800);

        scene.setFill(Color.web("#2d2d2d"));

        GameState gameState = new GameState(scene);
        gameState.start();

        stage.setScene(scene);
        stage.show();
    };

    public static void main(String[] args) {
        Application.launch();
    }
}