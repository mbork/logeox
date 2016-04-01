package pl.mbork.logeox;

import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by mbork on 01.04.16.
 */
public class TurtlePath {
    private Path path;
    private Paint paint;

    public TurtlePath() {
        this.path = new Path();
        this.paint = new Paint();
        this.paint.setStrokeWidth(6);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public TurtlePath(Path path) {
        this.path = path;
        this.paint.setStrokeWidth(10);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public TurtlePath(Path path, Paint paint) {
        this.path = path;
        this.paint = paint;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
