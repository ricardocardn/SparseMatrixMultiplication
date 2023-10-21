package software.ulpgc.bigdata.matrices.tests.multiplication;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.DoubleCoordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.IntCoordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.LongCoordinate;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseMatrixMultiplication;

public class MultiplicationTests {
    public static void main(String[] args) {
        Matrix compressedRowMatrix = defineCompressedRowMatrix();
        Matrix compressedColMatrix = defineCompressedColMatrix();

        System.out.println((new SparseMatrixMultiplication()).multiply(
                compressedRowMatrix,
                compressedColMatrix
        ).get().toString());
    }

    private static Matrix defineCompressedRowMatrix() {
        MatrixBuilder compressedRowMatrixBuilder = new CompressedRowMatrixBuilder(3);
        fillMatrix(compressedRowMatrixBuilder);

        Matrix compressedRowMatrix = compressedRowMatrixBuilder.getMatrix();
        System.out.println(compressedRowMatrix.get().toString());
        return compressedRowMatrix;
    }

    private static Matrix defineCompressedColMatrix() {
        MatrixBuilder compressedColMatrixBuilder = new CompressedColMatrixBuilder(3);
        fillMatrix(compressedColMatrixBuilder);

        Matrix compressedColMatrix = compressedColMatrixBuilder.getMatrix();
        System.out.println(compressedColMatrix.get().toString());
        return compressedColMatrix;
    }

    private static void fillMatrix(MatrixBuilder matrixBuilder) {
        matrixBuilder.set(new IntCoordinate(1,2,4));
        matrixBuilder.set(new IntCoordinate(1,1,1));
        matrixBuilder.set(new IntCoordinate(0,0,1));
    }
}
