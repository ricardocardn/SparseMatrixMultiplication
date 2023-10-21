package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

public class Transform2CCS implements MatrixTransformer {

    @Override
    public CompressedColMatrix execute(Matrix matrix) {
        if (! (matrix instanceof CompressedColMatrix)) return (CompressedColMatrix) transform((CompressedMatrix) matrix);
        else return (CompressedColMatrix) matrix;
    }

    private CompressedMatrix transform(CompressedMatrix matrix) {
        CompressedMatrixBuilder compressedColMatrixBuilder = new CompressedColMatrixBuilder(matrix.size());
        for (Coordinate coordinate : matrix.get()) {
            compressedColMatrixBuilder.set(coordinate);
        }

        return compressedColMatrixBuilder.getMatrix();
    }
}
