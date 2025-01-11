package com.hetlesaetherta.asteroids;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import java.util.ArrayList;

import static com.hetlesaetherta.asteroids.GameUtils.calculateFPS;
import static com.hetlesaetherta.asteroids.GameUtils.getFPS;

public class GameState {
    private Player player;
    private ArrayList<Entities> entities = new ArrayList<>();
    private final Scene scene;
    private Group root;
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

        root = new Group();
        root.getChildren().add(player.sprite);
        scene.setRoot(root);

        InputHandler inputHandler = new InputHandler(); // adds controls
        inputHandler.handler(scene, player);

        spawnAsteroid();
    }

    public void start() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Entities entity : entities) {
                    entity.update();
                    wrapAroundLogic(entity);
                    updateSprite(entity);
                    entity.checkCollision(entities);
                }
//                calculateFPS(now);
//                System.out.println("FPS: " + getFPS());
            }
        };
        animationTimer.start();
    }

    private void updateSprite(Entities entity) {
        double[] position = entity.getPosition();
        entity.sprite.setRotate(entity.getAngleDegrees());
        entity.sprite.setTranslateX(position[0]);
        entity.sprite.setTranslateY(position[1]);
    }

    private int asteroidCounter = 4; // initial asteroid spawn count

    private void spawnAsteroid() {
        if (entities.size() > 1) {
            return;
        }

        // TODO add logic to prevent entities[0] not Player

        Asteroid[] asteroidToSpawn = new Asteroid[asteroidCounter];

        for (int i = 0; i < asteroidCounter ; i++) {
            asteroidToSpawn[i] = new Asteroid(0,0, 360*Math.random(), 3);
            double[] xy = asteroidToSpawn[i].generateLegalSpawnPoint(player, scene);
            asteroidToSpawn[i].setPosition(xy[0], xy[1]);

            root.getChildren().add(asteroidToSpawn[i].sprite);
            entities.add(asteroidToSpawn[i]);
        }
        asteroidCounter++;
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
