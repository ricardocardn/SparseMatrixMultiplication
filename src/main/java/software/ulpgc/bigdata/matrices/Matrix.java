package software.ulpgc.bigdata.matrices;

import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.List;

public interface Matrix {
    void set(Coordinate coordinate);
    int size();
    List<Coordinate> get();
}
