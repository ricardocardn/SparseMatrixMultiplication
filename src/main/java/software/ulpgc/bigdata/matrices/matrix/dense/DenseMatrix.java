package software.ulpgc.bigdata.matrices.matrix.dense;

import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.lang.reflect.Type;

public class DenseMatrix<Type> implements Matrix<Type> {
    private final Object[][] matrix;
    private final int size;

    public DenseMatrix(int size) {
        this.size = size;
        matrix = new Object[size][size];
    }

    @Override
    public Type get(int i, int j) {
        return (Type) matrix[i][j];
    }

    public void set(Coordinate<Type> coordinate) {
        matrix[coordinate.i][coordinate.j] = coordinate.value;
    }

    @Override
    public int size() {
        return size;
    }
}
