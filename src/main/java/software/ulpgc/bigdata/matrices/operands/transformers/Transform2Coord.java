package software.ulpgc.bigdata.matrices.operands.transformers;

import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.dense.DenseMatrix;
import software.ulpgc.bigdata.matrices.operands.MatrixTransformer;

import java.util.ArrayList;

public class Transform2Coord<Type> implements MatrixTransformer<Type> {

    Transform2Coord(){}

    @Override
    public CoordinateMatrix<Type> execute(Matrix<Type> matrix) {
        if (matrix instanceof CompressedRowMatrix) return transformFromCRS((CompressedRowMatrix<Type>) matrix);
        else if (matrix instanceof CompressedColumnMatrix) return transformFromCCS((CompressedColumnMatrix<Type>) matrix);
        else return (CoordinateMatrix<Type>) matrix;
    }

    private CoordinateMatrix<Type> transformFromCCS(CompressedColumnMatrix<Type> matrix) {
        CoordinateMatrixBuilder<Type> matrixBuilder = new CoordinateMatrixBuilder<>(matrix.size());

        for (int i=0; i<matrix.size(); i++)
            for (int j=matrix.getColPointer().get(i); j<matrix.getColPointer().get(i+1); j++)
                matrixBuilder.set(new Coordinate<>(
                        matrix.getRows().get(j),
                        i,
                        matrix.getValues().get(j)
                ));

        return matrixBuilder.get();
    }

    private CoordinateMatrix<Type> transformFromCRS(CompressedRowMatrix<Type> matrix) {
        CoordinateMatrixBuilder<Type> matrixBuilder = new CoordinateMatrixBuilder<>(matrix.size());

        for (int i=0; i<matrix.size(); i++)
            for (int j=matrix.getRowPointer().get(i); j<matrix.getRowPointer().get(i+1); j++)
                matrixBuilder.set(new Coordinate<>(
                        i,
                        matrix.getColumns().get(j),
                        matrix.getValues().get(j)
                ));

        return matrixBuilder.get();
    }
}
