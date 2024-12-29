package com.hetlesaetherta.asteroids;

import javafx.scene.Node;

abstract class Entities {
    private double[] velocityVector = new double[2];
    private double[] positionVector = new double[2];
    private double angleDegrees;

    private double maxSpeed;
    protected Node sprite;

    public Entities(double x, double y, double angleDegrees, double maxSpeed) {
        this.positionVector[0] = x;
        this.positionVector[1] = y;
        this.angleDegrees = angleDegrees;
        this.maxSpeed = maxSpeed;
    }

    public void update() {

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


    public void setVelocity(double x, double y) {
        velocityVector[0] = x;
        velocityVector[1] = y;
    }

    public double[] getVelocity() {
        return velocityVector;
    }

    public void setAngleDegrees(double angleDegrees) {
        this.angleDegrees = angleDegrees;
    }

    public double getAngleDegrees() {
        return angleDegrees;
    }

    public void setPosition(double x, double y) {
        positionVector[0] = x;
        positionVector[1] = y;
    }

    public double[] getPosition() {
        return positionVector;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
