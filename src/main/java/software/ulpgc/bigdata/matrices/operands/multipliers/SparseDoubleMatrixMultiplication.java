package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CCS;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CRS;

public class SparseDoubleMatrixMultiplication implements MatrixMultiplication<Double> {
    private final Transform2CCS<Double> transform2CCS;
    private final Transform2CRS<Double> transform2CRS;

    public SparseDoubleMatrixMultiplication() {
        this.transform2CCS = new Transform2CCS<>();
        this.transform2CRS = new Transform2CRS<>();
    }

    @Override
    public Matrix<Double> multiply(Matrix<Double> A, Matrix<Double> B) {
        CompressedRowMatrix<Double> matrixA = transform2CRS.execute(A);
        CompressedColumnMatrix<Double> matrixB = transform2CCS.execute(B);

        CoordinateMatrixBuilder<Double> matrixBuilder = new CoordinateMatrixBuilder<>(matrixA.size());

        for (int i=0; i<matrixA.size(); i++) {
            for (int j=0; j<matrixB.size(); j++) {
                int ii = matrixA.rowPointer[i];
                int iEnd = matrixA.rowPointer[i+1];

                int jj = matrixB.colPointer[j];
                int jEnd = matrixB.colPointer[j+1];

                double s = 0;

                while (ii < iEnd && jj < jEnd) {
                    int column = matrixA.columns[ii];
                    int row = matrixB.rows[jj];

                    if (column == row) {
                        s += matrixA.values.get(ii) * matrixB.getValues().get(jj);
                        ii++;
                        jj++;
                    }
                    else if (column < row) ii++;
                    else jj++;
                }

                if (s != 0) {
                    matrixBuilder.set(new Coordinate<>(i, j, s));
                }
            }
        }

        return matrixBuilder.get();
    }
}
