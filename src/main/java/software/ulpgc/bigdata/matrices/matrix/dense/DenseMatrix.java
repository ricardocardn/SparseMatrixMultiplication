package software.ulpgc.bigdata.matrices.matrix.dense;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.StandardMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.List;

public class DenseMatrix implements StandardMatrix {
    private long[][] matrix;
    private int size;

    public DenseMatrix(int size) {
        this.size = size;
        matrix = new long[size][size];
    }

    @Override
    public void set(Coordinate coordinate) {
        matrix[coordinate.i][coordinate.j] = (Long) coordinate.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public long[][] get() {
        return matrix;
    }
}
