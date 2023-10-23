package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CoordinateMatrix<Type> implements Matrix<Type> {
    final int size;
    List<Coordinate<Type>> coordinateList;

    public CoordinateMatrix(int size, List<Coordinate<Type>> coordinateList) {
        this.size = size;
        this.coordinateList = coordinateList;
    }

    @Override
    public Type get(int i, int j) {
        for (Coordinate<Type> coordinate : coordinateList) {
            if (coordinate.i == i && coordinate.j == j)
                return coordinate.value;
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public List<Coordinate<Type>> get() {
        return coordinateList;
    }
}