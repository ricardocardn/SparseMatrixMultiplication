package software.ulpgc.bigdata.matrices.matrix.compressed.coordinates;

public abstract class Coordinate implements Comparable {
    public int i;
    public int j;
    public Object value;

    public Coordinate(int i, int j, Object value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public void transpose() {
        int c = i;
        i = j;
        j = c;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("i: ");
        stringBuilder.append(i);
        stringBuilder.append(", j: ");
        stringBuilder.append(j);
        stringBuilder.append(", value:");
        stringBuilder.append(value);

        return stringBuilder.toString();
    }

    public abstract Object getValue();

    @Override
    public int compareTo(Object object) {
        if (object instanceof Coordinate) {
            if (i == ((Coordinate) object).i && j == ((Coordinate) object).j && value == ((Coordinate) object).value)
                return 0;
            else return -1;
        }

        return -1;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Coordinate && this.compareTo(object) == 0;
    }
}
