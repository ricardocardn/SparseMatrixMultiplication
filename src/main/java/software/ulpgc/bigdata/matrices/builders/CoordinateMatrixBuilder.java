package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CoordinateMatrixBuilder<Type> implements MatrixBuilder<Type> {
    private final List<Coordinate<Type>> coordinateList;
    private final int size;

    public CoordinateMatrixBuilder(int size) {
        coordinateList = new ArrayList<>();
        this.size = size;
    }

    @Override
    public void set(Coordinate<Type> coordinate) {
        coordinateList.add(coordinate);
    }

    @Override
    public CoordinateMatrix<Type> get() {
        return new CoordinateMatrix<>(size,coordinateList);
    }
}
