package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.DenseMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;

public class ArrayDenseMatrixBuilder implements DenseMatrixBuilder {
    private DenseMatrix denseMatrix;

    public ArrayDenseMatrixBuilder(int size) {
        this.denseMatrix = new DenseMatrix(size);
    }


    @Override
    public void set(Coordinate coordinate) {
        denseMatrix.set(coordinate);
    }

    @Override
    public DenseMatrix getMatrix() {
        return denseMatrix;
    }
}
