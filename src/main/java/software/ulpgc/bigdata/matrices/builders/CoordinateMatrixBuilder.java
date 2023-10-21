package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

public class CoordinateMatrixBuilder implements CompressedMatrixBuilder {
    private CompressedMatrix matrix;

    public CoordinateMatrixBuilder(int size) {
        this.matrix = new CoordinateMatrix(size);
    }

    @Override
    public void set(Coordinate coordinate) {
        matrix.set(coordinate);
    }

    @Override
    public CompressedMatrix getMatrix() {
        return matrix;
    }
}
