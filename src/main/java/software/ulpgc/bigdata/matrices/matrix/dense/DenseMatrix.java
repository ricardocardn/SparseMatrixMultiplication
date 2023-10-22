package software.ulpgc.bigdata.matrices.matrix.dense;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.StandardMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.List;

public class DenseMatrix implements StandardMatrix {
    private final Double[][] matrix;
    private final int size;

    public DenseMatrix(int size) {
        this.size = size;
        matrix = new Double[size][size];
    }

    @Override
    public void set(Coordinate coordinate) {
        matrix[coordinate.i][coordinate.j] = coordinate.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Double[][] get() {
        return matrix;
    }
}
