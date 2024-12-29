package com.hetlesaetherta.asteroids;

import javafx.scene.shape.Circle;

public class Asteroid extends Entities{
    public Asteroid(double x, double y, double angleDegrees, double maxSpeed) {
        super(x, y, angleDegrees, maxSpeed);
        this.sprite = new Circle();
    }
}
