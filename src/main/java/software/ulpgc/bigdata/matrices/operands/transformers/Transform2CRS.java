package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

public class Transform2CRS implements MatrixTransformer {

    @Override
    public CompressedRowMatrix execute(Matrix matrix) {
        if (! (matrix instanceof CompressedRowMatrix)) return (CompressedRowMatrix) transform((CompressedMatrix) matrix);
        else return (CompressedRowMatrix) matrix;
    }

    private CompressedMatrix transform(CompressedMatrix matrix) {
        CompressedMatrixBuilder compressedRowMatrixBuilder = new CompressedRowMatrixBuilder(matrix.size());
        for (Coordinate coordinate : matrix.get()) {
            compressedRowMatrixBuilder.set(coordinate);
        }

        return compressedRowMatrixBuilder.getMatrix();
    }
}
