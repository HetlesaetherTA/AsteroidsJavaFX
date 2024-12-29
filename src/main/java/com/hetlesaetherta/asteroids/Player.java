package com.hetlesaetherta.asteroids;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Player extends Entities {
    private boolean rotateLeft;
    private boolean rotateRight;
    private boolean thrust;

    @Override
    public void update() {
        double[] velocityVector = getVelocity();
        double[] positionVector = getPosition();
        if (isThrust()) {
            addVelocity(0.50, getAngleDegrees());
        }

        if (isRotateLeft()) {
            setAngleDegrees(getAngleDegrees() - 5);
        }
        if (isRotateRight()) {
            setAngleDegrees(getAngleDegrees() + 5);
        }

        applyFriction(0.08);

        setPosition( // {param} = x, y
                positionVector[0] + velocityVector[0],
                positionVector[1] + velocityVector[1]
        );
    }

    public Player(double x, double y, double angleDegrees, double maxSpeed) {
        super(x, y, angleDegrees, maxSpeed);
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
        ((Path)this.sprite).setFill(Color.WHITE);
    }

    public void applyFriction(double friction) {
        double[] velocityVector = getVelocity();
        double speed = Math.sqrt(Math.pow(velocityVector[0], 2) + Math.pow(velocityVector[1], 2));

        if (speed > 0.0001) {
            double decrement = Math.min(friction, speed);

            velocityVector[0] -= (velocityVector[0] / speed) * decrement;
            velocityVector[1] -= (velocityVector[1] / speed) * decrement;

            if (speed - decrement < 0.0001) {
                velocityVector[0] = 0;
                velocityVector[1] = 0;
            }
        }
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