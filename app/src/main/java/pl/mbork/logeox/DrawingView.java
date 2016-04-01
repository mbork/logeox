package pl.mbork.logeox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import java.util.ArrayList;

public class DrawingView extends ImageView {

    final String TAG = "DrawingView";

    final LightingColorFilter turtlePenDownColorFilter = new LightingColorFilter(0xFFFFFFFF, 0x00888888);
    final LightingColorFilter turtlePenUpColorFilter = new LightingColorFilter(0xFFFFFFFF, 0x00DDDDDD);

    public Turtle turtle;
    private Bitmap turtleBitmap;
    public LightingColorFilter turtleColorFilter = turtlePenDownColorFilter; // init

    void initTurtle() {
        turtle = new Turtle(-90);
        turtleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.turtle_triangle);
        final ImageView drawingView = this;
        this.post(
                new Runnable() {
                    @Override
                    public void run() {
                        turtle.setPosition(new TurtlePoint(drawingView.getWidth() / 2, drawingView.getHeight() / 2));
                    }
                }
        );
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
        for (TurtlePath path: turtle.getPaths()) {
            canvas.drawPath(path.getPath(), path.getPaint());
        }
        Matrix turtleMatrix = new Matrix();
        Log.d(TAG, "Position: " + turtle.getPosition());
        turtleMatrix.setRotate((float) turtle.getDir() + 90);
        if (turtle.getPosition() != null) {
            turtleMatrix.preTranslate(-turtleBitmap.getScaledWidth(canvas) / 2,
                    -turtleBitmap.getScaledHeight(canvas) / 2);
            turtleMatrix.postTranslate((float) turtle.getPosition().getX(),
                    (float) turtle.getPosition().getY());
        }
        Paint turtlePaint = new Paint();
        turtlePaint.setColorFilter(turtleColorFilter);
        canvas.drawBitmap(turtleBitmap, turtleMatrix, turtlePaint);
    }
}
