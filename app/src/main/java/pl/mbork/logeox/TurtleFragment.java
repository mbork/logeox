package pl.mbork.logeox;

import android.app.Fragment;
import android.os.Bundle;

import java.util.ArrayDeque;

/**
 * This class's purpose is to make the turtle (in particular, the command list)
 * survive screen rotation.  See http://developer.android.com/guide/topics/resources/runtime-changes.html
 */
public class TurtleFragment extends android.support.v4.app.Fragment {
    private Turtle turtle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Turtle getTurtle() {
        return turtle;
    }

    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }
}
