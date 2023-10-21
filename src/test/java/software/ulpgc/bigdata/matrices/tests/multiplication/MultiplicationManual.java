package software.ulpgc.bigdata.matrices.tests.multiplication;

import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.DoubleCoordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.LongCoordinate;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CCS;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CRS;

public class MultiplicationManual {
    public static void main(String[] args) {
        CompressedMatrix compressedRowMatrix = defineCompressedRowMatrix();
        CompressedMatrix compressedColMatrix = defineCompressedColMatrix();

        System.out.println((new SparseMatrixMultiplication()).multiply(
                (new Transform2CRS()).execute(compressedRowMatrix),
                (new Transform2CCS()).execute(compressedColMatrix)
        ).get().toString());
    }

    private static CompressedMatrix defineCompressedRowMatrix() {
        CompressedMatrixBuilder compressedRowMatrixBuilder = new CoordinateMatrixBuilder(3);
        fillMatrix(compressedRowMatrixBuilder);

        CompressedMatrix compressedRowMatrix = compressedRowMatrixBuilder.getMatrix();
        System.out.println(compressedRowMatrix.get().toString());
        return compressedRowMatrix;
    }

    private static CompressedMatrix defineCompressedColMatrix() {
        CompressedMatrixBuilder compressedColMatrixBuilder = new CoordinateMatrixBuilder(3);
        fillMatrix(compressedColMatrixBuilder);

        CompressedMatrix compressedColMatrix = compressedColMatrixBuilder.getMatrix();
        System.out.println(compressedColMatrix.get().toString());
        return compressedColMatrix;
    }

    private static void fillMatrix(MatrixBuilder matrixBuilder) {
        matrixBuilder.set(new DoubleCoordinate(1,2,4));
        matrixBuilder.set(new DoubleCoordinate(1,1,1));
        matrixBuilder.set(new DoubleCoordinate(0,0,1));
        matrixBuilder.set(new DoubleCoordinate(2,2,3));
    }
}