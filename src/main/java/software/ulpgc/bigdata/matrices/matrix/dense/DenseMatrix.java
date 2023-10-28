package software.ulpgc.bigdata.matrices.matrix.dense;

import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.lang.reflect.Type;

public class DenseMatrix<Type> implements Matrix<Type> {
    private final Type[][] matrix;
    private final int size;

    public DenseMatrix(int size, Type[][] matrix) {
        this.size = size;
        this.matrix = matrix;
    }

    @Override
    public Type get(int i, int j) {
        return (Type) matrix[i][j];
    }

    @Override
    public int size() {
        return size;
    }

    public void set(Coordinate<Type> coordinate) {
        matrix[coordinate.i][coordinate.j] = coordinate.value;
    }
}
