package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixMultiplication<Type> implements MatrixMultiplication<Type> {
    @Override
    public Matrix<Type> multiply(CompressedRowMatrix<Type> matrixA, CompressedColumnMatrix<Type> matrixB) {
        CoordinateMatrixBuilder<Type> matrixBuilder = new CoordinateMatrixBuilder<>(matrixA.size());

        for (int i=0; i<matrixA.size(); i++) {
            for (int j=0; j<matrixB.size(); j++) {
                int ii = matrixA.getRowPointer().get(i);
                int iEnd = matrixA.getRowPointer().get(i+1);

                int jj = matrixB.getColPointer().get(j);
                int jEnd = matrixB.getColPointer().get(j+1);

                Double s = (double) 0;

                while (ii < iEnd && jj < jEnd) {
                    int column = matrixA.getColumns().get(ii);
                    int row = matrixB.getRows().get(jj);

                    if (column == row) {
                        s += (Double) matrixA.getValues().get(ii) * (Double) matrixB.getValues().get(jj);
                        ii++;
                        jj++;
                    }
                    else if (column < row) ii++;
                    else jj++;
                }

                if (s != 0) {
                    matrixBuilder.set(new Coordinate<>(i, j, (Type) s));
                }
            }
        }

        return matrixBuilder.get();
    }
}
