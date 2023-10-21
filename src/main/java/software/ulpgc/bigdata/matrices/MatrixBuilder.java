package software.ulpgc.bigdata.matrices;

import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

public interface MatrixBuilder {
    void set(Coordinate coordinate);
    Matrix getMatrix();
}
