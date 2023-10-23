package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColumnMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

import java.lang.reflect.Type;

public class Transform2CRS<Type> implements MatrixTransformer<Type> {
    private final Transform2Coord<Type> transform2Coord;

    public Transform2CRS(){
        this.transform2Coord = new Transform2Coord<>();
    }

    @Override
    public CompressedRowMatrix<Type> execute(Matrix<Type> matrix) {
        if (matrix instanceof CoordinateMatrix) return transformFromCoordinates(matrix);
        else if (matrix instanceof CompressedColumnMatrix) return transformFromCRS(matrix);
        else return (CompressedRowMatrix<Type>) matrix;
    }

    private CompressedRowMatrix<Type> transformFromCoordinates(Matrix<Type> matrix) {
        CompressedRowMatrixBuilder<Type> matrixBuilder = new CompressedRowMatrixBuilder<>(matrix.size());
        for (Coordinate<Type> coordinate : ((CoordinateMatrix<Type>) matrix).get()) {
            matrixBuilder.set(coordinate);
        }
        return matrixBuilder.get();
    }


    private CompressedRowMatrix<Type> transformFromCRS(Matrix<Type> matrix) {
        CoordinateMatrix<Type> coordinateMatrix = transform2Coord.execute(matrix);
        return transformFromCoordinates(coordinateMatrix);
    }
}
