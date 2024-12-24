package com.hetlesaetherta.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;

public class GameState {
    private final Player player;
    private final Scene scene;
    public GameState(Scene scene, Player player) {
        this.player = player;
        this.scene = scene;
    }

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


    public void start() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updatePlayerPosition();
                wrapAroundLogic(GameState.this.player);
                calculateFPS(now);
                System.out.println("FPS: " + fps);
            }
        };
        animationTimer.start();
    }

    private void updatePlayerPosition() {
        Path sprite = (Path) this.player.sprite;
        double[] velocityVector = player.getVelocity();

        if (player.isThrust()) {
            player.addVelocity(0.3, player.sprite.getRotate()); // param is acceleration speed
        }

        if (this.player.isRotateLeft()) {
            sprite.setRotate(sprite.getRotate() - 5);
        }

        if (this.player.isRotateRight()) {
            sprite.setRotate(sprite.getRotate() + 5);
        }

        applyFriction(player);
        sprite.setLayoutX(sprite.getLayoutX() + velocityVector[0]);
        sprite.setLayoutY(sprite.getLayoutY() + velocityVector[1]);
    }
    private void wrapAroundLogic(Entities gameObject) {
        if (gameObject.sprite.getLayoutX() > GameState.this.scene.getWidth()) {
            gameObject.sprite.setLayoutX(0);
        }

        if (gameObject.sprite.getLayoutY() > GameState.this.scene.getHeight()) {
            gameObject.sprite.setLayoutY(0);
        }
        if (gameObject.sprite.getLayoutX() < 0) {
            gameObject.sprite.setLayoutX(GameState.this.scene.getWidth());
        }
        if (gameObject.sprite.getLayoutY() < 0) {
            gameObject.sprite.setLayoutY(GameState.this.scene.getHeight());
        }
    }

    private void applyFriction(Entities gameObject) {
        gameObject.subtractVelocity(0.05);
    }
}
