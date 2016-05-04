package pl.mbork.logeox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class DrawingView extends ImageView {

    final String TAG = "DrawingView";

    private Turtle turtle;
    private Bitmap turtleBitmap;
    private Paint turtlePaint = new Paint();

    void initTurtle() {
        turtle = new Turtle(-90);
        turtleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.turtle_triangle);
        final ImageView drawingView = this;
        this.post(
                new Runnable() {
                    @Override
                    public void run() {
                        turtle.setStartPosition(new TurtlePoint(drawingView.getWidth() / 2, drawingView.getHeight() / 2));
                        turtle.goHome();  // This is suboptimal, since it adds a moveTo op to the path...
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

    public Turtle getTurtle() {
        return turtle;
    }

    private Boolean turtleOutOfBounds() {
        return ((turtle.getPosition().getX() < 0)
            || (turtle.getPosition().getX() > this.getWidth())
            || (turtle.getPosition().getY() < 0)
            || (turtle.getPosition().getY() > this.getHeight()));
    }

    private TurtlePoint projectTurtlePosition() {
        TurtlePoint projectedPosition = new TurtlePoint(turtle.getPosition());

        if (projectedPosition.getX() < 0) { projectedPosition.setX(0); }
        if (projectedPosition.getX() > this.getWidth()) { projectedPosition.setX(this.getWidth()); }

        if (projectedPosition.getY() < 0) { projectedPosition.setY(0); }
        if (projectedPosition.getY() > this.getHeight()) { projectedPosition.setY(this.getHeight()); }

        return projectedPosition;
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
        if (turtleOutOfBounds()) {
            Log.d(TAG, "Turtle out of bounds!");
            turtleMatrix.preScale(0.5f, 0.5f);
        }
        TurtlePoint projectedPosition = this.projectTurtlePosition();
        turtleMatrix.postRotate((float) turtle.getDir() + 90);
        if (turtle.getPosition() != null) {
            turtleMatrix.preTranslate(-turtleBitmap.getScaledWidth(canvas) / 2,
                    -turtleBitmap.getScaledHeight(canvas) / 2);
            turtleMatrix.postTranslate(projectedPosition.getX(),
                    projectedPosition.getY());
        }
        turtlePaint.setColorFilter(turtle.getTurtleColorFilter());
        canvas.drawBitmap(turtleBitmap, turtleMatrix, turtlePaint);
    }
}
