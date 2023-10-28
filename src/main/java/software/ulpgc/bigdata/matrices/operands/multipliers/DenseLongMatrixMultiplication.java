package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.DenseMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CCS;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CRS;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2Dense;

public class DenseLongMatrixMultiplication implements MatrixMultiplication<Long> {
    private final Transform2Dense<Long> transform2Dense;

    public DenseLongMatrixMultiplication() {
        transform2Dense = new Transform2Dense<>();
    }

    @Override
    public Matrix<Long> multiply(Matrix<Long> A, Matrix<Long> B) {
        DenseMatrix<Long> matrixA = transform2Dense.execute(A);
        DenseMatrix<Long> matrixB = transform2Dense.execute(B);

        Long[][] result = new Long[A.size()][B.size()];

        for (int i=0; i<matrixA.size(); i++)
             for (int k=0; k<matrixA.size(); k++)
                 for (int j=0; j<matrixA.size(); j++)
                     result[i][j] += matrixA.get(i,k)*matrixB.get(k,j);

        return new DenseMatrix<>(A.size(), result);
    }
}
