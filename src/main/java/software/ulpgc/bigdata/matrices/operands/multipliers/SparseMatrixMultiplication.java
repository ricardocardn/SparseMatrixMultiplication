package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.DoubleCoordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;

import java.util.ArrayList;
import java.util.List;

public class SparseMatrixMultiplication implements MatrixMultiplication {
    @Override
    public CompressedMatrix multiply(CompressedMatrix matrixA, CompressedMatrix matrixB) {
        CompressedRowMatrixBuilder compressedRowMatrixBuilder = new CompressedRowMatrixBuilder(matrixA.size());
        matrixA = (CompressedRowMatrix) matrixA;

        List<Integer> aRowPtr = getRowPointer((CompressedRowMatrix) matrixA);
        List<Integer> bColPtr = getColPointer((CompressedColMatrix) matrixB);

        List<Coordinate> aCoordinates = ((CompressedRowMatrix) matrixA).get();
        List<Coordinate> bCoordinates = ((CompressedColMatrix) matrixB).get();

        for (int i=0; i<matrixA.size(); i++) {
            for (int j=0; j<matrixB.size(); j++) {
                int ii = aRowPtr.get(i);
                int iEnd = aRowPtr.get(i+1);

                int jj = bColPtr.get(j);
                int jEnd = bColPtr.get(j+1);

                long s = 0;

                while (ii < iEnd && jj < jEnd) {
                    int aa = aCoordinates.get(ii).j;
                    int bb = bCoordinates.get(jj).i;

                    if (aa == bb) {
                        s += (Double) aCoordinates.get(ii).value * (Double) bCoordinates.get(jj).value;
                        ii++;
                        jj++;
                    }
                    else if (aa < bb) ii++;
                    else jj++;
                }

                if (s != 0) compressedRowMatrixBuilder.set(new DoubleCoordinate(i,j,s));
            }
        }

        return compressedRowMatrixBuilder.getMatrix();
    }

    public List<Integer> getRowPointer(CompressedRowMatrix matrix) {
        List<Integer> rowPointer = new ArrayList<>();

        int i = 0;
        int cur = 0;
        rowPointer.add(i);

        for (Coordinate coordinate : matrix.get()) {
            if (coordinate.i != cur) {
                for (int j = 0; j < (coordinate.i - cur); j++)
                    rowPointer.add(i);
                cur = coordinate.i;
            }
            i++;
        }

        for (int j = 0; j < matrix.size(); j++)
            rowPointer.add(i);
        return rowPointer;
    }

    public List<Integer> getColPointer(CompressedColMatrix matrix) {
        List<Integer> colPointer = new ArrayList<>();

        int i = 0;
        int cur = 0;
        colPointer.add(i);

        for (Coordinate coordinate : matrix.get()) {
            if (coordinate.j != cur) {
                for (int j = 0; j < (coordinate.j - cur); j++)
                    colPointer.add(i);
                cur = coordinate.j;
            }
            i++;
        }

        for (int j = 0; j < matrix.size(); j++)
            colPointer.add(i);

        return colPointer;
    }
}
