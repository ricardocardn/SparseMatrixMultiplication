package software.ulpgc.bigdata.matrices.matrix.compressed.coordinates;

public class DoubleCoordinate extends Coordinate {
    public DoubleCoordinate(int i, int j, double value) {
        super(i, j, value);
    }

    public Double getValue() {
        return (Double) super.value;
    }
}
