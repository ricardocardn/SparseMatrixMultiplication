package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CoordinateMatrix implements Matrix {
    private int size;
    private List<Coordinate> coordinates;

    public CoordinateMatrix(int size) {
        this.size = size;
        this.coordinates = new ArrayList<>();
    }

    @Override
    public void set(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    @Override
    public int size() {
        return size;
    }

    public List<Coordinate> get() {
        return coordinates;
    }
}
