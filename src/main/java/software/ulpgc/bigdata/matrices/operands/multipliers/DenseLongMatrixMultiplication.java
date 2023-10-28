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

public class DenseLongMatrixMultiplication implements MatrixMultiplication<Long> {
    public DenseLongMatrixMultiplication() {}

    @Override
    public Matrix<Long> multiply(Matrix<Long> A, Matrix<Long> B) {
        DenseMatrix<Long> matrixA = (DenseMatrix<Long>) A;
        DenseMatrix<Long> matrixB = (DenseMatrix<Long>) B;

        Long[][] result = new Long[A.size()][B.size()];

        for (int i=0; i<matrixA.size(); i++)
             for (int k=0; k<matrixA.size(); k++)
                 for (int j=0; j<matrixA.size(); j++)
                     result[i][j] += matrixA.get(i,k)*matrixB.get(k,j);

        return new DenseMatrix<>(A.size(), result);
    }
}
