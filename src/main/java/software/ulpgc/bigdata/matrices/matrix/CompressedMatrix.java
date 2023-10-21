package software.ulpgc.bigdata.matrices.matrix;

import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.List;

public interface CompressedMatrix extends Matrix {
    List<Coordinate> get();
}
