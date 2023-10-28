package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateMatrix<?> that = (CoordinateMatrix<?>) o;
        return size == that.size && Objects.equals(coordinateList, that.coordinateList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, coordinateList);
    }
}