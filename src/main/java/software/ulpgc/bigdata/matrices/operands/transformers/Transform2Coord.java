package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

public class Transform2Coord implements MatrixTransformer {

    @Override
    public Matrix execute(Matrix matrix) {
        if (! (matrix instanceof CoordinateMatrix)) return transform(matrix);
        else return matrix;
    }

    private Matrix transform(Matrix matrix) {
        MatrixBuilder coordinateMatrixBuilder = new CoordinateMatrixBuilder(matrix.size());
        for (Coordinate coordinate : matrix.get()) {
            coordinateMatrixBuilder.set(coordinate);
        }

        return coordinateMatrixBuilder.getMatrix();
    }
}
