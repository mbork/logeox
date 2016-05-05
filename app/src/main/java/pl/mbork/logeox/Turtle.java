package pl.mbork.logeox;

import android.graphics.LightingColorFilter;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * A Turtle can turn, go forward and draw or not.  It also remembers the path it went.
 * Currently, there is only one such path; when changing the line color/style is implemented,
 * more will be needed.
 */
public class Turtle {
    private TurtlePoint startPosition, position;
    private float startDir, dir; // 0 means right
    private Boolean penIsDown;
    private ArrayDeque<TurtleCommand> commands;
    private List<TurtlePath> paths;
    private TurtlePath currentPath;

    final LightingColorFilter TURTLE_PEN_DOWN_COLOR_FILTER = new LightingColorFilter(0xFFFFFFFF, 0x00888888);
    final LightingColorFilter TURTLE_PEN_UP_COLOR_FILTER = new LightingColorFilter(0xFFFFFFFF, 0x00DDDDDD);

    final String TAG = "Turtle";

    private abstract class TurtleCommand {
        float arg;
	    boolean undoable;
        public TurtleCommand(float arg) {
            this.arg = arg;
        };
        abstract void Execute();
        public boolean isUndoable() {
	    return undoable;
        };
    }

    private class GoForward extends TurtleCommand {
        public GoForward(float distance) {
            super(distance);
	    undoable = true;
        }
        void Execute() {
            Log.d(TAG, "GoForward.Execute");
            position.setX(position.getX() + arg * (float)Math.cos(Math.toRadians(dir)));
            position.setY(position.getY() + arg * (float)Math.sin(Math.toRadians(dir)));
            if(penIsDown) {
                currentPath.lineTo(position);
            } else {
                currentPath.moveTo(position);
            }
            Log.d(TAG, "new position: (" + position.getX() + "," + position.getY() + ")");
        }
    }

    private class TurnLeft extends TurtleCommand {
        public TurnLeft(float angle) {
	    super(angle);
	    undoable = false;
        }
        void Execute() {
            Log.d(TAG, "TurnLeft.Execute");
            dir -= arg;
            Log.d(TAG, "new angle: " + dir);
        }
    }

    private class TurnRight extends TurtleCommand {
        public TurnRight(float angle) {
	    super(angle);
	    undoable = false;
        }
        void Execute() {
            Log.d(TAG, "TurnRight.Execute");
            dir += arg;
            Log.d(TAG, "new angle: " + dir);
        }
    }

    private class PenDown extends TurtleCommand {
        public PenDown(float arg) {
	    super(arg);
	    undoable = false;
        }
        void Execute() {
            penIsDown = true;
        }
    }

    private class PenUp extends TurtleCommand {
        public PenUp(float arg) {
	    super(arg);
	    undoable = false;
        }
        void Execute() {
            penIsDown = false;
        }
    }

    public Turtle(float dir) { // needed because the position will only be known later in DrawingView
        this(new TurtlePoint(0, 0), dir);
    }

    public Turtle(TurtlePoint startPosition, float dir) {
        this.startPosition = new TurtlePoint(startPosition);
        this.position = new TurtlePoint(startPosition);
        this.startDir = dir;
        this.dir = dir;
        this.penIsDown = true;
        currentPath = new TurtlePath();
        paths = new ArrayList<TurtlePath>();
        commands = new ArrayDeque<>();
    }

    public void setStartPosition(TurtlePoint startPosition) {
        this.startPosition = new TurtlePoint(startPosition);
    }

    public void setPosition(TurtlePoint position) {
        this.position = new TurtlePoint(position);
        currentPath.moveTo(position);
    }

    public void goHome() {
        Log.d(TAG, "Going home: " + this.startPosition);
        this.setPosition(this.startPosition);
        this.dir = startDir;
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

    public void goForward(float distance) {
        Log.d(TAG, "goForward");
        TurtleCommand cmd = new GoForward(distance);
        cmd.Execute();
        commands.addLast(cmd);
    }

    public void turnLeft(float angle) {
        Log.d(TAG, "turnLeft");
        TurtleCommand cmd = new TurnLeft(angle);
        cmd.Execute();
        commands.addLast(cmd);
    }

    public void turnRight(float angle) {
        Log.d(TAG, "turnRight");
        TurtleCommand cmd = new TurnRight(angle);
        cmd.Execute();
        commands.addLast(cmd);
    }

    public void penDown() {
        TurtleCommand cmd = new PenDown(0);
        cmd.Execute();
        commands.addLast(cmd);
    }

    public void penUp() {
        TurtleCommand cmd = new PenUp(0);
        cmd.Execute();
        commands.addLast(cmd);
    }

    public void clearTurtlePaths() {
        paths.clear();
        currentPath = new TurtlePath();
        currentPath.moveTo(position);
    };

    public void replayCommands() {
        clearTurtlePaths();
        this.penIsDown = true; // TODO: remove magic!
        goHome();
        for (TurtleCommand cmd: commands) {
            cmd.Execute();
        }
    }

    public void Undo() {
        TurtleCommand lastCmd;

        Log.d(TAG, "Undo");
        do {
            lastCmd = commands.pollLast();
        } while ((lastCmd != null) && !(lastCmd.isUndoable()));
        replayCommands();
    }
}
