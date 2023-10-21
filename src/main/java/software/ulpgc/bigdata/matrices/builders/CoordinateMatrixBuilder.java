package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

public class CoordinateMatrixBuilder implements MatrixBuilder {
    private Matrix matrix;

    public CoordinateMatrixBuilder(int size) {
        this.matrix = new CoordinateMatrix(size);
    }

    @Override
    public void set(Coordinate coordinate) {
        matrix.set(coordinate);
    }

    @Override
    public Matrix getMatrix() {
        return matrix;
    }
}
