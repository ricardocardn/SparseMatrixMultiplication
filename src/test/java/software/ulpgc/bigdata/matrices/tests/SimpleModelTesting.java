package software.ulpgc.bigdata.matrices.tests;

import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.LongCoordinate;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseMatrixMultiplication;

public class SimpleModelTesting {
    public static void main(String[] args) {
        CompressedMatrix coordinateMatrix = defineCoordinateMatrix();
        CompressedMatrix compressedRowMatrix = defineCompressedRowMatrix();
        CompressedMatrix compressedColMatrix = defineCompressedColMatrix();

        System.out.println((new SparseMatrixMultiplication()).getColPointer(
                (CompressedColMatrix) compressedColMatrix
        ));

        System.out.println((new SparseMatrixMultiplication()).multiply(
                compressedRowMatrix,
                compressedColMatrix
        ).get().toString());
    }

    private static CompressedMatrix defineCoordinateMatrix() {
        CompressedMatrixBuilder coordinateMatrixBuilder = new CoordinateMatrixBuilder(10);
        fillMatrix(coordinateMatrixBuilder);

        CompressedMatrix coordinateMatrix = coordinateMatrixBuilder.getMatrix();
        System.out.println(coordinateMatrix.get().toString());
        return coordinateMatrix;
    }

    private static CompressedMatrix defineCompressedRowMatrix() {
        CompressedMatrixBuilder compressedRowMatrixBuilder = new CompressedRowMatrixBuilder(10);
        fillMatrix(compressedRowMatrixBuilder);

        CompressedMatrix compressedRowMatrix = compressedRowMatrixBuilder.getMatrix();
        System.out.println(compressedRowMatrix.get().toString());
        return compressedRowMatrix;
    }

    private static CompressedMatrix defineCompressedColMatrix() {
        CompressedColMatrixBuilder compressedColMatrixBuilder = new CompressedColMatrixBuilder(10);
        fillMatrix(compressedColMatrixBuilder);

        CompressedMatrix compressedColMatrix = compressedColMatrixBuilder.getMatrix();
        System.out.println(compressedColMatrix.get().toString());
        return compressedColMatrix;
    }

    private static void fillMatrix(MatrixBuilder matrixBuilder) {
        matrixBuilder.set(new LongCoordinate(0,0,1));
        matrixBuilder.set(new LongCoordinate(1,3,2));
        matrixBuilder.set(new LongCoordinate(0,2,3));
        matrixBuilder.set(new LongCoordinate(0,3,4));
        matrixBuilder.set(new LongCoordinate(1,0,5));
        matrixBuilder.set(new LongCoordinate(2,0,5));
        matrixBuilder.set(new LongCoordinate(7,0,5));
        matrixBuilder.set(new LongCoordinate(7,0,5));
        matrixBuilder.set(new LongCoordinate(10,0,5));
    }
}
