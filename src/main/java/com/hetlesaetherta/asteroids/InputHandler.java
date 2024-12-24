package com.hetlesaetherta.asteroids;

import javafx.scene.Scene;

public class InputHandler {
    public void handler(Scene scene, Player player) {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W, UP: player.startThrust(); break;
                case A, LEFT: player.startRotateLeft(); break;
                case D, RIGHT: player.startRotateRight(); break;
            }
        });

        scene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case W, UP: player.stopThrust(); break;
                case A, LEFT: player.stopRotateLeft(); break;
                case D, RIGHT: player.stopRotateRight(); break;
            }
        });
    }
}
