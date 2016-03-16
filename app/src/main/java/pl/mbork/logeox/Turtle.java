package pl.mbork.logeox;

import android.util.Log;

public class Turtle {
    public TurtlePoint position;
    private double dir; // 0 means right

    final String TAG = "Turtle";

    public Turtle(TurtlePoint position) {
        this.position = position;
        dir = -90;
    }

    public double getDir() {
        return dir;
    }

    public TurtlePoint getPosition() {
        return position;
    }

    public TurtlePoint goForward(double distance) {
        position.setX(position.getX() + distance * Math.cos(Math.toRadians(dir)));
        position.setY(position.getY() + distance * Math.sin(Math.toRadians(dir)));
        Log.d(TAG, "new position: (" + position.getX() + "," + position.getY() + ")");
        return position;
    }

    public void turnLeft(double angle) {
        dir -= angle;
        Log.d(TAG, "new angle: " + dir);
    }

    public void turnRight(double angle) {
        dir += angle;
        Log.d(TAG, "new angle: " + dir);
    }
}
