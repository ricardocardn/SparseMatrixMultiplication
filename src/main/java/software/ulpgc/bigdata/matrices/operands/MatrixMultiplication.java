package software.ulpgc.bigdata.matrices.operands;

import software.ulpgc.bigdata.matrices.Matrix;

public interface MatrixMultiplication {
    Matrix multiply(Matrix matrixA, Matrix matrixB);
}
