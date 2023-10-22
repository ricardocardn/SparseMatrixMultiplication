package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CoordinateMatrix extends SparseMatrix {
    public CoordinateMatrix(int size) {
        super(size);
    }

    @Override
    public void set(Coordinate coordinate) {
        super.coordinateList.add(coordinate);
    }

    @Override
    public int size() {
        return size;
    }

    public List<Coordinate> get() {
        return super.coordinateList;
    }
}
