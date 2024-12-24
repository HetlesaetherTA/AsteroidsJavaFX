package com.hetlesaetherta.asteroids;

import javafx.scene.Node;
import javafx.scene.Scene;

public class Entities {
    private double[] velocityVector = {0,0};
    private double maxSpeed;
    protected Node sprite;

    public Entities(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void addVelocity(double increment, double angleDegrees) {
        double incrementX = Math.cos(Math.toRadians(angleDegrees - 90)) * increment;
        double incrementY = Math.sin(Math.toRadians(angleDegrees - 90)) * increment;

        velocityVector[0] += incrementX;
        velocityVector[1] += incrementY;

        double speed = Math.sqrt(Math.pow(velocityVector[0], 2) + Math.pow(velocityVector[1], 2));

        if (speed > this.maxSpeed && speed != 0) {
            velocityVector[0] *= this.maxSpeed / speed;
            velocityVector[1] *= this.maxSpeed / speed;
        }
    }

    public void subtractVelocity(double friction) {
        double speed = Math.sqrt(Math.pow(velocityVector[0], 2) + Math.pow(velocityVector[1], 2));

        if (speed > 0.0001) { // Avoid division by zero or unnecessary calculations
            // Calculate the friction decrement, clamping to avoid negative magnitudes
            double decrement = Math.min(friction, speed);

            // Scale the velocity components proportionally
            velocityVector[0] -= (velocityVector[0] / speed) * decrement;
            velocityVector[1] -= (velocityVector[1] / speed) * decrement;

            // If the velocity magnitude is reduced to nearly zero, set to zero
            if (speed - decrement < 0.0001) {
                velocityVector[0] = 0;
                velocityVector[1] = 0;
            }
        }
    }

    public void setVelocity(double x, double y) {
        velocityVector[0] = x;
        velocityVector[1] = y;
    }

    public double[] getVelocity() {
        return velocityVector;
    }
}
