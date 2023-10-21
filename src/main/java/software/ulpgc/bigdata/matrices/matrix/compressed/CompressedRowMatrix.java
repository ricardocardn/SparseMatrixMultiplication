package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.comparators.CoordinateRowComparator;

import java.util.List;

public class CompressedRowMatrix extends SparseMatrix {

    public CompressedRowMatrix(int size) {
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
        super.coordinateList.sort(new CoordinateRowComparator());
        return super.coordinateList;
    }
}
