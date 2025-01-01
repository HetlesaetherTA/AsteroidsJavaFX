package com.hetlesaetherta.asteroids;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Asteroid extends Entities{
    public Asteroid(double x, double y, double angleDegrees, double maxSpeed) {
        super(x, y, angleDegrees, maxSpeed);
        this.sprite = new Circle();
        ((Circle) this.sprite).setRadius(60);
        ((Circle) this.sprite).setFill(null);
        ((Circle) this.sprite).setStroke(Color.WHITE);
        ((Circle) this.sprite).setStrokeWidth(2);
    }

    @Override
    public void update() {
        super.update();
        setVelocity(
                getMaxSpeed() * Math.cos(Math.toRadians(getAngleDegrees())),
                getMaxSpeed() * Math.sin(Math.toRadians(getAngleDegrees()))
        );

        setPosition(
            getPosition()[0] + getVelocity()[0],
            getPosition()[1] + getVelocity()[1]
        );

    }

}
