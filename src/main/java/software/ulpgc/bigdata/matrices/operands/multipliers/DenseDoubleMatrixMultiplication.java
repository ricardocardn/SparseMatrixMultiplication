package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;

public class DenseDoubleMatrixMultiplication implements MatrixMultiplication<Double> {
    public DenseDoubleMatrixMultiplication() {}

    @Override
    public Matrix<Double> multiply(Matrix<Double> A, Matrix<Double> B) {
        DenseMatrix<Double> matrixA = (DenseMatrix<Double>) A;
        DenseMatrix<Double> matrixB = (DenseMatrix<Double>) B;

        Double[][] result = new Double[A.size()][A.size()];

        for (int i=0; i<matrixA.size(); i++)
             for (int k=0; k<matrixA.size(); k++)
                 for (int j=0; j<matrixA.size(); j++)
                     result[i][j] += matrixA.get(i,j)*matrixB.get(i,j);

        return new DenseMatrix<>(A.size(), result);
    }
}
