package pl.mbork.logeox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.ArrayList;

public class DrawingView extends ImageView {

    Paint paint = new Paint();
    public Turtle turtle;
    private ArrayList<TurtleLine> lines = new ArrayList<TurtleLine>();

    Paint paint = new Paint(); // TODO: move to Turtle!

    public DrawingView(Context context) {
        super(context);
//        turtle = new Turtle(new TurtlePoint(getWidth()/2,getHeight()/2));
        turtle = new Turtle(new TurtlePoint(600,900));
        paint.setStrokeWidth(5); // TODO: move to Turtle!
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        turtle = new Turtle(new TurtlePoint(getWidth()/2,getHeight()/2));
        turtle = new Turtle(new TurtlePoint(600,900));
        paint.setStrokeWidth(5);
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        turtle = new Turtle(new TurtlePoint(getWidth()/2,getHeight()/2));
        turtle = new Turtle(new TurtlePoint(600,900));
        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (TurtleLine line: lines) {
            canvas.drawLine((float)line.getStart().getX(), (float)line.getStart().getY(),
                    (float)line.getEnd().getX(), (float)line.getEnd().getY(), paint);
        }
    }

    public void addLine(TurtleLine turtleLine) {
        lines.add(turtleLine);
    }
}
