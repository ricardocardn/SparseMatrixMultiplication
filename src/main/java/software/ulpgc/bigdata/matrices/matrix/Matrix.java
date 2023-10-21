package software.ulpgc.bigdata.matrices.matrix;

import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

public interface Matrix {
    void set(Coordinate coordinate);
    int size();
}
