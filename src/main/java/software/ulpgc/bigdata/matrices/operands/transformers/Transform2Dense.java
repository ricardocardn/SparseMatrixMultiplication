package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.builders.DenseMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

public class Transform2Dense<Type> implements MatrixTransformer<Type> {
    private final Transform2Coord<Type> transform2Coord;

    public Transform2Dense(){
        this.transform2Coord = new Transform2Coord<>();
    }

    @Override
    public DenseMatrix<Type> execute(Matrix<Type> matrix) {
        if (matrix instanceof CompressedColumnMatrix || matrix instanceof CompressedRowMatrix)
            return transformFromCompressed(matrix);
        else if (matrix instanceof CoordinateMatrix) return transformFromCoordinate(matrix);
        else return (DenseMatrix<Type>) matrix;
    }

    private DenseMatrix<Type> transformFromCoordinate(Matrix<Type> matrix) {
        DenseMatrixBuilder<Type> denseMatrixBuilder = new DenseMatrixBuilder<>(matrix.size());
        for (Coordinate<Type> coordinate : ((CoordinateMatrix<Type>) matrix).get()) {
            denseMatrixBuilder.set(coordinate);
        }

        return denseMatrixBuilder.get();
    }

    private DenseMatrix<Type> transformFromCompressed(Matrix<Type> matrix) {
        CoordinateMatrix<Type> coordinateMatrix = transform2Coord.execute(matrix);
        return transformFromCoordinate(coordinateMatrix);
    }
}
