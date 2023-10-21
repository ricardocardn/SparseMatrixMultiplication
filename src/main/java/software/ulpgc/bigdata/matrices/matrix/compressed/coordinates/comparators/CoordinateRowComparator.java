package software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.comparators;

import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.Comparator;

public class CoordinateRowComparator implements Comparator {
    @Override
    public int compare(Object coordinate1, Object coordinate2) {
        int comparison = ((Coordinate) coordinate1).i - ((Coordinate) coordinate2).i;
        if (comparison == 0)
            return ((Coordinate) coordinate1).j - ((Coordinate) coordinate2).j;
        return comparison;
    }
}
