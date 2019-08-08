package graphtpm.structure;

import java.awt.*;
import java.util.Objects;

public class PointPair {
    Point P1;
    Point P2;

    public PointPair(Point P1, Point P2){
        this.P1=P1;
        this.P2=P2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(P1, P2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointPair pointPair = (PointPair) o;
        return (Objects.equals(P1, pointPair.P1) && Objects.equals(P2, pointPair.P2)) ||
               (Objects.equals(P1, pointPair.P2) && Objects.equals(P2, pointPair.P1));
    }
}
