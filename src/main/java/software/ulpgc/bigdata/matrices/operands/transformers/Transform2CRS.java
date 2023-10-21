package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

public class Transform2CRS implements MatrixTransformer {

    @Override
    public Matrix execute(Matrix matrix) {
        if (! (matrix instanceof CompressedRowMatrix)) return transform(matrix);
        else return matrix;
    }

    private Matrix transform(Matrix matrix) {
        MatrixBuilder compressedRowMatrixBuilder = new CompressedRowMatrixBuilder(matrix.size());
        for (Coordinate coordinate : matrix.get()) {
            compressedRowMatrixBuilder.set(coordinate);
        }

        return compressedRowMatrixBuilder.getMatrix();
    }
}
