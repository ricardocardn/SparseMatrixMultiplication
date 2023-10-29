package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2Dense;

public class DenseDoubleMatrixMultiplication implements MatrixMultiplication<Double> {
    private final Transform2Dense<Double> transform2Dense;

    public DenseDoubleMatrixMultiplication() {
        transform2Dense = new Transform2Dense<>();
    }

    @Override
    public Matrix<Double> multiply(Matrix<Double> A, Matrix<Double> B) {
        DenseMatrix<Double> matrixA = transform2Dense.execute(A);
        DenseMatrix<Double> matrixB = transform2Dense.execute(B);

        Double[][] result = new Double[matrixA.size()][matrixB.size()];

        for (int i=0; i<matrixA.size(); i++)
             for (int k=0; k<matrixA.size(); k++)
                 for (int j=0; j<matrixA.size(); j++)
                     try {
                         result[i][j] = result[i][j] + matrixA.get(i, k) * matrixB.get(k, j);

                     } catch (NullPointerException e) {}

        return new DenseMatrix<>(A.size(), result);
    }
}
