package software.ulpgc.bigdata.matrices.matrix.compressed.coordinates;

import java.util.Objects;

public class Coordinate<Type> {
    public int i;
    public int j;
    public Type value;

    public Coordinate(int i, int j, Type value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public String toString() {
        return "i: " +i +
                ", j: " + j +
                ", value:" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate<?> that = (Coordinate<?>) o;
        return i == that.i && j == that.j && Math.abs((Double) value - (Double) that.value) < 0.00000001;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, value);
    }
}
