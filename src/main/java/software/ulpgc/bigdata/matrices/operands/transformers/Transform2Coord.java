package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.LongCoordinate;
import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

public class Transform2Coord implements MatrixTransformer {

    @Override
    public CompressedMatrix execute(Matrix matrix) {
        if (matrix instanceof DenseMatrix) return transform((DenseMatrix) matrix);
        else return (CompressedMatrix) matrix;
    }

    private CompressedMatrix transform(DenseMatrix matrix) {
        CompressedMatrixBuilder compressedMatrixBuilder = new CoordinateMatrixBuilder(matrix.size());
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = 0; j < matrix.size(); j++) {
                compressedMatrixBuilder.set(
                        new LongCoordinate(i, j, matrix.get()[i][j])
                );
            }
        }

        return compressedMatrixBuilder.getMatrix();
    }
}
