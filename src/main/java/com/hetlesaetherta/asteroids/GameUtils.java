package com.hetlesaetherta.asteroids;

public class GameUtils {
    // fps counter
    private static double fps = 0;
    private static int frameCounter = 0;
    private static double timeSinceLastSecond = 0;
    private static double lastTimeStamp = 0;

    public static double getFPS() {
        return fps;
    }

    public static void calculateFPS(long time) {
        timeSinceLastSecond += time - lastTimeStamp;
        frameCounter++;

        if (timeSinceLastSecond > 0) {
            fps = frameCounter / (timeSinceLastSecond / 1_000_000_000);
        }

        if (timeSinceLastSecond >= 1_000_000_000) {
            timeSinceLastSecond = 0;
            frameCounter = 0;
        }
        lastTimeStamp = time;
    }

}
