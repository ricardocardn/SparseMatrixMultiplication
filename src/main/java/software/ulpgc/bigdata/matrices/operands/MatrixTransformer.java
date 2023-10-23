package software.ulpgc.bigdata.matrices.operands;

import software.ulpgc.bigdata.matrices.matrix.Matrix;

public interface MatrixTransformer<Type> {
    Matrix<Type> execute(Matrix<Type> matrix);
}
