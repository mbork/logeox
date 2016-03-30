package pl.mbork.logeox;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    final String TAG = "Main";
    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = (DrawingView)findViewById(R.id.drawing_view);
        drawingView.invalidate();

        findViewById(R.id.turn_left).setOnClickListener(buttonLeft_ocl);
        findViewById(R.id.turn_right).setOnClickListener(buttonRight_ocl);
        findViewById(R.id.go_forward).setOnClickListener(buttonForward_ocl);
    }

    final View.OnClickListener buttonLeft_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawingView.turtle.turnLeft(45);
            drawingView.invalidate();
        }
    };

    final View.OnClickListener buttonRight_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            drawingView.turtle.turnRight(45);
            drawingView.invalidate();
        }
    };

    final View.OnClickListener buttonForward_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TurtlePoint oldpos = new TurtlePoint(drawingView.turtle.getPosition());
            TurtlePoint newpos = new TurtlePoint(drawingView.turtle.goForward(100));
            Log.d(TAG, "Adding line from" + oldpos + " to " + newpos);
            drawingView.invalidate();
        }
    };
}
