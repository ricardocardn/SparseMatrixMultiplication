package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CCS;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CRS;

public class SparseLongMatrixMultiplication implements MatrixMultiplication<Long> {
    private final Transform2CCS<Long> transform2CCS;
    private final Transform2CRS<Long> transform2CRS;

    public SparseLongMatrixMultiplication() {
        this.transform2CCS = new Transform2CCS<>();
        this.transform2CRS = new Transform2CRS<>();
    }


    @Override
    public Matrix<Long> multiply(Matrix<Long> A, Matrix<Long> B) {
        CompressedRowMatrix<Long> matrixA = transform2CRS.execute(A);
        CompressedColumnMatrix<Long> matrixB = transform2CCS.execute(B);

        CoordinateMatrixBuilder<Long> matrixBuilder = new CoordinateMatrixBuilder<>(matrixA.size());

        for (int i=0; i<matrixA.size(); i++) {
            for (int j=0; j<matrixB.size(); j++) {
                int ii = matrixA.getRowPointer().get(i);
                int iEnd = matrixA.getRowPointer().get(i+1);

                int jj = matrixB.getColPointer().get(j);
                int jEnd = matrixB.getColPointer().get(j+1);

                long s = 0;

                while (ii < iEnd && jj < jEnd) {
                    int column = matrixA.getColumns().get(ii);
                    int row = matrixB.getRows().get(jj);

                    if (column == row) {
                        s += matrixA.getValues().get(ii) * matrixB.getValues().get(jj);
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
