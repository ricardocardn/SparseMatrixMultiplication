package software.ulpgc.bigdata.matrices.operands;

import software.ulpgc.bigdata.matrices.Matrix;

public interface MatrixTransformer {
    Matrix execute(Matrix matrix);
}
