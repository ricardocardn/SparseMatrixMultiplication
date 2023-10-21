package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

public class Transform2CCS implements MatrixTransformer {

    @Override
    public Matrix execute(Matrix matrix) {
        if (! (matrix instanceof CompressedColMatrix)) return transform(matrix);
        else return matrix;
    }

    private Matrix transform(Matrix matrix) {
        MatrixBuilder compressedColMatrixBuilder = new CompressedColMatrixBuilder(matrix.size());
        for (Coordinate coordinate : matrix.get()) {
            compressedColMatrixBuilder.set(coordinate);
        }

        return compressedColMatrixBuilder.getMatrix();
    }
}
