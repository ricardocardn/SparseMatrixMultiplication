package software.ulpgc.bigdata.matrices.operands;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.Operator;
import software.ulpgc.bigdata.matrices.matrix.compressed.SparseMatrix;
import software.ulpgc.bigdata.matrices.operands.multipliers.CoordinateMatrixMultiplication;

public class CompressedMatrixOperator implements Operator {
    private final MatrixMultiplication compressedMatrixMultiplication;

    public CompressedMatrixOperator() {
        this.compressedMatrixMultiplication = new CoordinateMatrixMultiplication();
    }

    public CompressedMatrix multiply(CompressedMatrix matrixA, CompressedMatrix matrixB) {
        if (matrixA instanceof SparseMatrix && matrixB instanceof SparseMatrix)
            return compressedMatrixMultiplication.multiply(matrixA, matrixB);

        return null;
    }
}
