package software.ulpgc.bigdata.matrices.operands;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.matrix.Matrix;

public interface MatrixTransformer {
    CompressedMatrix execute(Matrix matrix);
}
