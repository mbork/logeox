package pl.mbork.logeox;

public class TurtleLine {
    private TurtlePoint start, end;

    public TurtleLine(TurtlePoint start, TurtlePoint end) {
        this.start = start;
        this.end = end;
    }

    public TurtlePoint getEnd() {
        return end;
    }

    public TurtlePoint getStart() {
        return start;
    }

    public void setEnd(TurtlePoint end) {
        this.end = end;
    }

    public void setStart(TurtlePoint start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return start + "--" + end;
    }
}
