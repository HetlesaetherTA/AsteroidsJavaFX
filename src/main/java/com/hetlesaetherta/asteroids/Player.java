package com.hetlesaetherta.asteroids;

import javafx.scene.Scene;
import javafx.scene.shape.*;

public class Player extends Entities {
    private boolean rotateLeft;
    private boolean rotateRight;
    private boolean thrust;
    private double maxSpeed;

    public Player(double maxSpeed) {
        super(maxSpeed);
        this.sprite = new Path();

        ((Path) this.sprite).getElements().addAll(
                new MoveTo( // Top point (centered at 0, 0)
                        0,
                        -25
                ),
                new QuadCurveTo( // Bottom-left
                        -10, 0, // Control point
                        -20, 25 // Bottom-left point
                ),
                new LineTo( // Bottom-middle
                        0,
                        10
                ),
                new LineTo( // Bottom-right
                        20,
                        25
                ),
                new QuadCurveTo( // Curve to top
                        10, 0, // Control point
                        0, -25 // Back to top point
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
    public double getMaxSpeed() {
        return maxSpeed;
    }
}