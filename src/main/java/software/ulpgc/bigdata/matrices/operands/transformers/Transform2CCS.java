package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColumnMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

public class Transform2CCS<Type> implements MatrixTransformer<Type> {
    private final Transform2Coord<Type> transform2Coord;

    public Transform2CCS(){
        this.transform2Coord = new Transform2Coord<>();
    }

    @Override
    public CompressedColumnMatrix<Type> execute(Matrix<Type> matrix) {
        if (matrix instanceof CoordinateMatrix) return transformFromCoordinates(matrix);
        else if (matrix instanceof CompressedRowMatrix) return transformFromCRS(matrix);
        else return (CompressedColumnMatrix<Type>) matrix;
    }

    private CompressedColumnMatrix<Type> transformFromCoordinates(Matrix<Type> matrix) {
        MatrixBuilder<Type> matrixBuilder = new CompressedColumnMatrixBuilder<>(matrix.size());
        for (Coordinate<Type> coordinate : ((CoordinateMatrix<Type>) matrix).get()) {
            matrixBuilder.set(coordinate);
        }
        return (CompressedColumnMatrix<Type>) matrixBuilder.get();
    }


    private CompressedColumnMatrix<Type> transformFromCRS(Matrix<Type> matrix) {
        CoordinateMatrix<Type> coordinateMatrix = transform2Coord.execute(matrix);
        return transformFromCoordinates(coordinateMatrix);
    }
}
