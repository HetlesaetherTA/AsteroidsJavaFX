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

    public void substractVelocity(double x, double y) {
        velocityVector[0] -= x;
        velocityVector[1] -= y;
    }

    public void setVelocity(double x, double y) {
        velocityVector[0] = x;
        velocityVector[1] = y;
    }

    public double[] getVelocity() {
        return velocityVector;
    }
}
