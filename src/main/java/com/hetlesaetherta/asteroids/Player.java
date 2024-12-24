package com.hetlesaetherta.asteroids;

import javafx.scene.Scene;
import javafx.scene.shape.*;

public class Player extends Entities {
    private boolean rotateLeft;
    private boolean rotateRight;
    private boolean thrust;

    public Player(Scene scene) {
        super();
        this.sprite = new Path();

        ((Path) this.sprite).getElements().addAll(
                new MoveTo( // top point
                        scene.getWidth() / 2,
                        scene.getHeight() / 2 - 25
                ),
                new QuadCurveTo( // bottom-left
                        scene.getWidth() / 2 - 10, scene.getHeight() / 2, // Control point
                        scene.getWidth() / 2 - 20, scene.getHeight() / 2 + 25 // Bottom-left point
                ),

                new LineTo( // Line to bottom-middle
                        scene.getWidth() / 2,
                        scene.getHeight() / 2 + 10 // Bottom-middle point
                ),
                new LineTo( //  bottom-right
                        scene.getWidth() / 2 + 20,
                        scene.getHeight() / 2 + 25 // Bottom-right point
                ),
                new QuadCurveTo( // Curve to top
                        scene.getWidth() / 2 + 10, scene.getHeight() / 2, // Control point
                        scene.getWidth() / 2, scene.getHeight() / 2 - 25 // Back to top point
                ),
                new ClosePath() // Close shape
        );
    }
    public void startThrust() {
        this.thrust = true;
    }

    public void startRotateLeft() {
        this.rotateLeft = true;
    }

    public void startRotateRight() {
        this.rotateRight = true;
    }
    public void stopThrust() {
        this.thrust = false;
    }

    public void stopRotateLeft() {
        this.rotateLeft = false;
    }

    public void stopRotateRight() {
        this.rotateRight = false;
    }
    public Boolean isThrust() {
        return thrust;
    };

    public Boolean isRotateLeft() {
        return rotateLeft;
    };

    public Boolean isRotateRight() {
        return rotateRight;
    };
}