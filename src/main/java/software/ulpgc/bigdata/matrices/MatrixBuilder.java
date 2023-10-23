package software.ulpgc.bigdata.matrices;

import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

public interface MatrixBuilder<Type> {
    void set(Coordinate<Type> coordinate);
    Matrix<Type> get();
}
