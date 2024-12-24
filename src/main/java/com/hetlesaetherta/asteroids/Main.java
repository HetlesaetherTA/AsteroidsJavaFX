package com.hetlesaetherta.asteroids;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, 800,800);
        Player player = new Player(scene);

        InputHandler inputHandler = new InputHandler();
        inputHandler.handler(scene, player);

        GameState gameState = new GameState(player);
        gameState.start();
        root.getChildren().add(player.sprite);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}