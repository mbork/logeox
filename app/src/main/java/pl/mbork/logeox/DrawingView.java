package pl.mbork.logeox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import java.util.ArrayList;

public class DrawingView extends ImageView {

    final String TAG = "DrawingView";

    public Turtle turtle;

    Paint paint = new Paint(); // TODO: move to Turtle!

    void initTurtle() {
        turtle = new Turtle(-90);
        paint.setStyle(Paint.Style.STROKE);
        final ImageView drawingView = this;
        this.post(
                new Runnable() {
                    @Override
                    public void run() {
                        turtle.setPosition(new TurtlePoint(drawingView.getWidth()/2,drawingView.getHeight()/2));
                    }
                }
        );
        paint.setStrokeWidth(5); // TODO: move to Turtle!
    }

    public DrawingView(Context context) {
        super(context);
        initTurtle();
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTurtle();
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTurtle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "Redrawing...");
        for (Path path: turtle.getPaths()) {
            canvas.drawPath(path, paint);
        }
        Log.d(TAG, "Width: " + getWidth() + ", height: " + getHeight());
    }
}
