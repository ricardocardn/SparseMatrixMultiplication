package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;

public abstract class SparseMatrix implements Matrix {
    final int size;
    List<Coordinate> coordinateList;

    public SparseMatrix(int size) {
        this.size = size;
        this.coordinateList = new ArrayList<>();
    }

    @Override
    public void set(Coordinate coordinate) {
        coordinateList.add(coordinate);
    }

    @Override
    public int size() {
        return size;
    }

    public List<Coordinate> get() {
        return coordinateList;
    }
}
