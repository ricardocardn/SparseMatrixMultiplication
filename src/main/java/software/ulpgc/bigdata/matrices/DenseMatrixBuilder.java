package software.ulpgc.bigdata.matrices;

import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;

public interface DenseMatrixBuilder extends MatrixBuilder {
    DenseMatrix getMatrix();
}
