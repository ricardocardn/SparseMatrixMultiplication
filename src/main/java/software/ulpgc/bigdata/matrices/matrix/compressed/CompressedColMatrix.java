package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.comparators.CoordinateColComparator;

import java.util.List;

public class CompressedColMatrix extends SparseMatrix {
    int size;

    public CompressedColMatrix(int size) {
        super(size);
    }

    public List<Coordinate> get() {
        super.coordinateList.sort(new CoordinateColComparator());
        return super.coordinateList;
    }
}
