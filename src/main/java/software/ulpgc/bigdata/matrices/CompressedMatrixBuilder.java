package software.ulpgc.bigdata.matrices;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;

public interface CompressedMatrixBuilder extends MatrixBuilder {
    CompressedMatrix getMatrix();
}
