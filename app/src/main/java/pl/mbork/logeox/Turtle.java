package pl.mbork.logeox;

import android.graphics.LightingColorFilter;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * A Turtle can turn, go forward and draw or not.  It also remembers the path it went.
 * Currently, there is only one such path; when changing the line color/style is implemented,
 * more will be needed.
 */
public class Turtle {
    private TurtlePoint position;
    private float dir; // 0 means right
    private Boolean penIsDown;
    private List<TurtlePath> paths;
    private TurtlePath currentPath;

    final LightingColorFilter TURTLE_PEN_DOWN_COLOR_FILTER = new LightingColorFilter(0xFFFFFFFF, 0x00888888);
    final LightingColorFilter TURTLE_PEN_UP_COLOR_FILTER = new LightingColorFilter(0xFFFFFFFF, 0x00DDDDDD);

    final String TAG = "Turtle";

    public Turtle(float dir) { // needed because the position will only be known later in DrawingView
        this(new TurtlePoint(0, 0), dir);
    }

    public Turtle(TurtlePoint position, float dir) {
        this.position = position;
        this.dir = dir;
        this.penIsDown = true;
        currentPath = new TurtlePath();
        paths = new ArrayList<TurtlePath>();
    }

    public void setPosition(TurtlePoint position) {
        this.position = position;
        currentPath.moveTo(position);
    }

    public double getDir() {
        return dir;
    }

    public TurtlePoint getPosition() {
        return position;
    }

    public LightingColorFilter getTurtleColorFilter() {
        if (penIsDown) {
            return TURTLE_PEN_DOWN_COLOR_FILTER;
        } else {
            return TURTLE_PEN_UP_COLOR_FILTER;
        }
    }

    public List<TurtlePath> getPaths() {
        List<TurtlePath> paths = new ArrayList<TurtlePath>(this.paths);
        paths.add(currentPath);
        return paths;
    }

    public TurtlePoint goForward(float distance) {
        position.setX(position.getX() + distance * (float)Math.cos(Math.toRadians(dir)));
        position.setY(position.getY() + distance * (float)Math.sin(Math.toRadians(dir)));
        if(penIsDown) {
            currentPath.lineTo(position);
        } else {
            currentPath.moveTo(position);
        }
        Log.d(TAG, "new position: (" + position.getX() + "," + position.getY() + ")");
        return position;
    }

    public void turnLeft(float angle) {
        dir -= angle;
        Log.d(TAG, "new angle: " + dir);
    }

    public void turnRight(float angle) {
        turnLeft(-angle);
    }

    public void penDown() {
        penIsDown = true;
    }

    public void penUp() {
        penIsDown = false;
    }
}
