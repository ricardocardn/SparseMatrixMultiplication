package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.SparseMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

public class CompressedColMatrixBuilder implements MatrixBuilder {
    private SparseMatrix sparseMatrix;

    public CompressedColMatrixBuilder(int size) {
        this.sparseMatrix = new CompressedColMatrix(size);
    }

    @Override
    public void set(Coordinate coordinate) {
        sparseMatrix.set(coordinate);
    }

    @Override
    public SparseMatrix getMatrix() {
        return sparseMatrix;
    }
}
