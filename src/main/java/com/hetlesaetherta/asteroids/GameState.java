package com.hetlesaetherta.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import java.util.ArrayList;

public class GameState {
    private Player player;
    private ArrayList<Entities> entities = new ArrayList<>();
    private final Scene scene;

    public GameState(Scene scene) {
        this.scene = scene;
        initiate();
    }

    private void initiate() {
        this.player = new Player(
                scene.getWidth() / 2,
                scene.getHeight() / 2,
                0,
                8);

        entities.add(this.player);

        double[] pos = player.getPosition();

        Group root = new Group();
        root.getChildren().add(player.sprite);
        scene.setRoot(root);

        InputHandler inputHandler = new InputHandler();
        inputHandler.handler(scene, player);
    }

    public void start() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Entities entity : entities) {
                    entity.update();
                    wrapAroundLogic(entity);
                    updateSprite(entity);
                }
              calculateFPS(now);
              System.out.println("FPS: " + fps);
            }
        };
        animationTimer.start();
    }
    // fps counter
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

    private void updateSprite(Entities entity) {
        double[] position = entity.getPosition();
        entity.sprite.setRotate(entity.getAngleDegrees());
        entity.sprite.setTranslateX(position[0]);
        entity.sprite.setTranslateY(position[1]);
    }
    private void wrapAroundLogic(Entities entity) {
        double[] positionVector = entity.getPosition();
        if (positionVector[0] > GameState.this.scene.getWidth()) {
            entity.setPosition(
                    positionVector[0] - GameState.this.scene.getWidth(),
                    positionVector[1]);
        }

        if (positionVector[1] > GameState.this.scene.getHeight()) {
            entity.setPosition(
                    positionVector[0],
                    positionVector[1] - GameState.this.scene.getHeight());
        }
        if (positionVector[0] < 0) {
            entity.setPosition(
                    positionVector[0] + GameState.this.scene.getWidth(),
                    positionVector[1]);
        }
        if (positionVector[1] < 0) {
            entity.setPosition(
                    positionVector[0],
                    positionVector[1] + GameState.this.scene.getHeight());
        }
    }
}
