package pl.mbork.logeox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by mbork on 10.03.16.
 */
public class DrawingView extends ImageView {

    Paint paint = new Paint();

    public DrawingView(Context context) {
        super(context);
//        paint.setColor(100);
//        paint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, getHeight(), getWidth(), 0, paint);
    }

//    public DrawingView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public DrawingView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }

    /*public DrawingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        paint.setColor(100);
        paint.setStrokeWidth(5);
    }*/
}
