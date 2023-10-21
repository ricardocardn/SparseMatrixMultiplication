package software.ulpgc.bigdata.matrices.matrix.compressed.coordinates;

public class IntCoordinate extends Coordinate {
    public IntCoordinate(int i, int j, int value) {
        super(i, j, value);
    }

    public int getValue() {
        return (Integer) super.value;
    }
}
