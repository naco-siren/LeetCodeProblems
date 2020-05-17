package base;

/**
 * Created by naco_siren on 9/18/17.
 */
public class Interval {
    public int start;
    public int end;
    public Interval() { start = 0; end = 0; }
    public Interval(int s, int e) { start = s; end = e; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Interval) {
            Interval other = (Interval) obj;
            return this.start == other.start && this.end == other.end;
        } else {
            return super.equals(obj);
        }
    }
}
