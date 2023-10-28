package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;

import java.util.ArrayList;
import java.util.List;

public class DenseMatrixBuilder<Type> implements MatrixBuilder<Type> {
    private final Type[][] array;
    private final int size;

    public DenseMatrixBuilder(int size) {
        array = (Type[][]) new Object[size][size];
        this.size = size;
    }

    @Override
    public void set(Coordinate<Type> coordinate) {
        array[coordinate.i][coordinate.j] = coordinate.value;
    }

    @Override
    public DenseMatrix<Type> get() {
        return new DenseMatrix<Type>(size, array);
    }
}
