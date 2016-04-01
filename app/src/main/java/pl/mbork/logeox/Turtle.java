package pl.mbork.logeox;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Turtle {
    public TurtlePoint position;
    private double dir; // 0 means right

    private List<TurtlePath> paths;
    private TurtlePath currentPath;

    final String TAG = "Turtle";

    public Turtle(TurtlePoint position, double dir) {
        this.position = position;
        this.dir = dir;
        currentPath = new TurtlePath();
        paths = new ArrayList<TurtlePath>();
    }

    public Turtle(double dir) { // needed because the position will only be known later in DrawingView
        this.dir = dir;
        currentPath = new TurtlePath();
        paths = new ArrayList<TurtlePath>();
    }

    public void setPosition(TurtlePoint position) {
        this.position = position;
        currentPath.getPath().moveTo((float)position.getX(),(float)position.getY());
    }

    public double getDir() {
        return dir;
    }

    public TurtlePoint getPosition() {
        return position;
    }

    public List<TurtlePath> getPaths() {
        List<TurtlePath> paths = new ArrayList<TurtlePath>(this.paths);
        paths.add(currentPath);
        return paths;
    }

    public TurtlePoint goForward(double distance) {
        position.setX(position.getX() + distance * Math.cos(Math.toRadians(dir)));
        position.setY(position.getY() + distance * Math.sin(Math.toRadians(dir)));
        currentPath.getPath().lineTo((float)position.getX(), (float)position.getY());
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
