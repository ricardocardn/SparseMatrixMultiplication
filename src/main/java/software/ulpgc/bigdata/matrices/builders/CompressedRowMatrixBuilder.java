package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.SparseMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

public class CompressedRowMatrixBuilder implements MatrixBuilder {
    private SparseMatrix sparseMatrix;

    public CompressedRowMatrixBuilder(int size) {
        sparseMatrix = new CompressedRowMatrix(size);
    }

    @Override
    public void set(Coordinate coordinate) {
        sparseMatrix.set(coordinate);
    }

    @Override
    public Matrix getMatrix() {
        return sparseMatrix;
    }
}
