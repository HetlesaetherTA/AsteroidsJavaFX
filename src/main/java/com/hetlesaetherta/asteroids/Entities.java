package com.hetlesaetherta.asteroids;

import javafx.scene.Node;
import javafx.scene.Scene;

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

    public double[] generateLegalSpawnPoint(Player player, Scene scene) {
        double maxX = scene.getWidth() * 0.3;
        double maxY = scene.getHeight() * 0.3; // limit spawn range to 30%/2 distance to boarder

        double x = 0, y = 0;
        double playerToXYDistance = calculateDistance(player, scene, x, y);

        while (x+y == 0 || playerToXYDistance < 0.15*(scene.getWidth() + scene.getHeight())/2) { // checks object is too close to player
            x = maxX * Math.random();
            y = maxY * Math.random();
        }

        if (x > maxX/2) {
            x += scene.getWidth() - maxX;
        }

        if (y > maxY/2) {
            y += scene.getHeight() - maxY;
        }

        return new double[]{x, y};
    }
    private double calculateDistance(Player player, Scene scene, double x2, double y2) {
        double[] playerPosition = player.getPosition();
        double x1 = playerPosition[0];
        double y1 = playerPosition[1];

        double distanceXtoCenter = scene.getWidth() - x1;
        double distanceYtoCenter = scene.getHeight() - y1;

        // normalize to center
        x1 = x1 - distanceXtoCenter;
        y1 = y1 - distanceYtoCenter;

        x2 = x2 - distanceXtoCenter;
        y2 = y2 - distanceYtoCenter;

        if (x2 > scene.getWidth()) {
            x2 = 0 + (x2 - scene.getWidth());
        }

        if (x2 < 0) {
            x2 = scene.getWidth() - x2;
        }

        if (y2 > scene.getHeight()) {
           y2 = 0 + (y2 - scene.getHeight());
        }

        if (y2 < 0) {
           y2 = scene.getHeight() - y2;
        }

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double calculateDistance(double x2, double y2) {
        double x1 = this.positionVector[0];
        double y1 = this.positionVector[1];



        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); // euclidean distance formula
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
