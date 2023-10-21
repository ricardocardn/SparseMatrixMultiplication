package software.ulpgc.bigdata.matrices.matrix.compressed.coordinates;

public abstract class Coordinate {
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
}
