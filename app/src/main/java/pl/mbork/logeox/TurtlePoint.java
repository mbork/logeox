package pl.mbork.logeox;

public class TurtlePoint {
    private double x, y;

    public TurtlePoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public TurtlePoint(TurtlePoint tp) {
        this.x = tp.getX();
        this.y = tp.getY();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}
