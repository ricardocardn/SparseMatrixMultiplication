package software.ulpgc.bigdata.matrices.tests;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.DoubleCoordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.IntCoordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.LongCoordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CRS;

import javax.xml.crypto.dsig.Transform;
import javax.xml.transform.Transformer;

public class SimpleModelTesting {
    public static void main(String[] args) {
        Matrix coordinateMatrix = defineCoordinateMatrix();
        Matrix compressedRowMatrix = defineCompressedRowMatrix();
        Matrix compressedColMatrix = defineCompressedColMatrix();

        System.out.println((new SparseMatrixMultiplication()).getColPointer(
                (CompressedColMatrix) compressedColMatrix
        ));

        System.out.println((new SparseMatrixMultiplication()).multiply(
                compressedRowMatrix,
                compressedColMatrix
        ).get().toString());
    }

    private static Matrix defineCoordinateMatrix() {
        MatrixBuilder coordinateMatrixBuilder = new CoordinateMatrixBuilder(10);
        fillMatrix(coordinateMatrixBuilder);

        Matrix coordinateMatrix = coordinateMatrixBuilder.getMatrix();
        System.out.println(coordinateMatrix.get().toString());
        return coordinateMatrix;
    }

    private static Matrix defineCompressedRowMatrix() {
        MatrixBuilder compressedRowMatrixBuilder = new CompressedRowMatrixBuilder(10);
        fillMatrix(compressedRowMatrixBuilder);

        Matrix compressedRowMatrix = compressedRowMatrixBuilder.getMatrix();
        System.out.println(compressedRowMatrix.get().toString());
        return compressedRowMatrix;
    }

    private static Matrix defineCompressedColMatrix() {
        MatrixBuilder compressedColMatrixBuilder = new CompressedColMatrixBuilder(10);
        fillMatrix(compressedColMatrixBuilder);

        Matrix compressedColMatrix = compressedColMatrixBuilder.getMatrix();
        System.out.println(compressedColMatrix.get().toString());
        return compressedColMatrix;
    }

    private static void fillMatrix(MatrixBuilder matrixBuilder) {
        matrixBuilder.set(new IntCoordinate(0,0,1));
        matrixBuilder.set(new DoubleCoordinate(1,3,2));
        matrixBuilder.set(new IntCoordinate(0,2,3));
        matrixBuilder.set(new LongCoordinate(0,3,4));
        matrixBuilder.set(new IntCoordinate(1,0,5));
        matrixBuilder.set(new IntCoordinate(2,0,5));
        matrixBuilder.set(new IntCoordinate(7,0,5));
        matrixBuilder.set(new IntCoordinate(7,0,5));
        matrixBuilder.set(new IntCoordinate(10,0,5));
    }
}
