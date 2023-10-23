package software.ulpgc.bigdata.matrices.operands;

import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;

public interface MatrixMultiplication<Type> {
    Matrix<Type> multiply(CompressedRowMatrix<Type> matrixA, CompressedColumnMatrix<Type> matrixB);
}
