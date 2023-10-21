package software.ulpgc.bigdata.matrices.matrix.compressed.coordinates;

public class LongCoordinate extends Coordinate {
    public LongCoordinate(int i, int j, long value) {
        super(i, j, value);
    }

    public long getValue() {
        return (Long) super.value;
    }
}
