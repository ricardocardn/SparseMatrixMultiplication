package software.ulpgc.bigdata.matrices.operands.multipliers;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;

public class CoordinateMatrixMultiplication implements MatrixMultiplication {
    @Override
    public CompressedMatrix multiply(CompressedMatrix matrixA, CompressedMatrix matrixB) {
        CoordinateMatrixBuilder coordinateMatrixBuilder = new CoordinateMatrixBuilder(matrixA.size());
        matrixA = (CoordinateMatrix) matrixA;
        matrixB = (CoordinateMatrix) matrixB;

        // Multiply

        return coordinateMatrixBuilder.getMatrix();
    }
}
