package software.ulpgc.bigdata.matrices.operands;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;

public interface MatrixMultiplication {
    CompressedMatrix multiply(CompressedMatrix matrixA, CompressedMatrix matrixB);
}
