package pl.mbork.logeox;

public class TurtlePoint {
    private float x, y;

    public TurtlePoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public TurtlePoint(TurtlePoint tp) {
        this.x = tp.getX();
        this.y = tp.getY();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}
