package com.hetlesaetherta.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;

public class GameState {
    private final Player player;

    private double fps = 0;
    private int frameCounter = 0;
    private double timeSinceLastSecond = 0;
    private double lastTimeStamp = 0;

    public double getFPS() {
        return fps;
    }

    private void calculateFPS(long time) {
        timeSinceLastSecond += time - this.lastTimeStamp;
        this.frameCounter++;

        if (this.timeSinceLastSecond > 0) {
            this.fps = frameCounter / (this.timeSinceLastSecond / 1_000_000_000);
        }

        if (this.timeSinceLastSecond >= 1_000_000_000) {
            this.timeSinceLastSecond = 0;
            this.frameCounter = 0;
        }
        this.lastTimeStamp = time;
    }
    public GameState(Player player) {
        this.player = player;
    }

    public void start() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updatePlayerPosition();
                calculateFPS(now);
                System.out.println("FPS: " + fps);
            }
        };
        animationTimer.start();
    }

    private void updatePlayerPosition() {
        Path sprite = (Path) this.player.sprite;

        if (this.player.isThrust()) {
            sprite.setLayoutX(sprite.getLayoutX()+5*Math.cos(Math.toRadians(sprite.getRotate()-90)));
            sprite.setLayoutY(sprite.getLayoutY()+5*Math.sin(Math.toRadians(sprite.getRotate()-90)));
        }
        if (this.player.isRotateLeft()) {
            sprite.setRotate(sprite.getRotate() - 5);
        }

        if (this.player.isRotateRight()) {
            sprite.setRotate(sprite.getRotate() + 5);
        }
    }
}
