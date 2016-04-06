package pl.mbork.logeox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    final String TAG = "Main";
    private DrawingView drawingView;

    final float ROTATION_UNIT = 30;
    final float MOVEMENT_UNIT = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = (DrawingView)findViewById(R.id.drawing_view);

        findViewById(R.id.turn_left).setOnClickListener(buttonLeft_ocl);
        findViewById(R.id.turn_right).setOnClickListener(buttonRight_ocl);
        findViewById(R.id.go_forward).setOnClickListener(buttonForward_ocl);
        findViewById(R.id.pen_down).setOnClickListener(buttonPenDown_ocl);
        findViewById(R.id.pen_up).setOnClickListener(buttonPenUp_ocl);
    }

    final View.OnClickListener buttonLeft_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawingView.getTurtle().turnLeft(ROTATION_UNIT);
            drawingView.invalidate();
        }
    };

    final View.OnClickListener buttonRight_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawingView.getTurtle().turnRight(ROTATION_UNIT);
            drawingView.invalidate();
        }
    };

    final View.OnClickListener buttonForward_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TurtlePoint oldpos = new TurtlePoint(drawingView.getTurtle().getPosition());
            TurtlePoint newpos = new TurtlePoint(drawingView.getTurtle().goForward(MOVEMENT_UNIT));
            Log.d(TAG, "Adding line from" + oldpos + " to " + newpos);
            drawingView.invalidate();
        }
    };

    final View.OnClickListener buttonPenDown_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawingView.getTurtle().penDown();
            drawingView.invalidate();
        }
    };

    final View.OnClickListener buttonPenUp_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawingView.getTurtle().penUp();
            drawingView.invalidate();
        }
    };
}
