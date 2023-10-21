package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.LongCoordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;

public class CoordinateMatrixMultiplication implements MatrixMultiplication {
    @Override
    public Matrix multiply(Matrix matrixA, Matrix matrixB) {
        CoordinateMatrixBuilder coordinateMatrixBuilder = new CoordinateMatrixBuilder(matrixA.size());
        matrixA = (CoordinateMatrix) matrixA;
        matrixB = (CoordinateMatrix) matrixB;

        // Multiply

        return coordinateMatrixBuilder.getMatrix();
    }
}
