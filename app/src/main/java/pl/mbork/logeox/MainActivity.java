package pl.mbork.logeox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Main";
    private DrawingView drawingView;
    private Switch penSwitch;

    final float ROTATION_UNIT = 30;
    final float MOVEMENT_UNIT = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = (DrawingView)findViewById(R.id.drawing_view);
        penSwitch = (Switch)findViewById(R.id.pen_switch);

        findViewById(R.id.turn_left).setOnClickListener(buttonLeft_ocl);
        findViewById(R.id.turn_right).setOnClickListener(buttonRight_ocl);
        findViewById(R.id.go_forward).setOnClickListener(buttonForward_ocl);
        penSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drawingView.getTurtle().penDown();
                } else {
                    drawingView.getTurtle().penUp();
                }
                drawingView.invalidate();
            }
        });

        findViewById(R.id.pen_down).setOnClickListener(buttonPenDown_ocl);
        findViewById(R.id.pen_up).setOnClickListener(buttonPenUp_ocl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_clear_screen:
                Log.d(TAG, "Clear screen!");
                drawingView.getTurtle().clearTurtlePaths();
                drawingView.invalidate();
                return true;
            case R.id.action_undo:
                Log.d(TAG, "Undo!");
                drawingView.getTurtle().Undo();
                drawingView.invalidate();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            drawingView.getTurtle().goForward(MOVEMENT_UNIT);
            TurtlePoint newpos = new TurtlePoint(drawingView.getTurtle().getPosition());
            Log.d(TAG, "Adding line from" + oldpos + " to " + newpos);
            drawingView.invalidate();
        }
    };

    final View.OnClickListener buttonPenDown_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            penSwitch.setChecked(true);
        }
    };

    final View.OnClickListener buttonPenUp_ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            penSwitch.setChecked(false);
        }
    };
}
