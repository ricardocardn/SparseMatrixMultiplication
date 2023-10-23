package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CompressedRowMatrixBuilder<Type> implements MatrixBuilder<Type> {
    private final List<Coordinate<Type>> coordinateList;
    private final int size;

    public CompressedRowMatrixBuilder(int size) {
        this.coordinateList = new ArrayList<>();
        this.size = size;
    }

    @Override
    public void set(Coordinate<Type> coordinate) {
        coordinateList.add(coordinate);
    }

    @Override
    public CompressedRowMatrix<Type> get() {
        coordinateList.sort((c1, c2) -> c1.i - c2.i == 0 ? c1.j - c2.j : c1.i - c2.i);
        List<Integer> rowPointer = getRowPointer();

        List<Integer> cols = new ArrayList<>();
        List<Type> values = new ArrayList<>();

        for (Coordinate<Type> coordinate : coordinateList) {
            cols.add(coordinate.j);
            values.add(coordinate.value);
        }

        return new CompressedRowMatrix<>(size, rowPointer, cols, values);
    }

    public List<Integer> getRowPointer() {
        List<Integer> rowPointer = new ArrayList<>();

        int i = 0;
        int cur = 0;
        rowPointer.add(i);

        for (Coordinate<Type> coordinate : coordinateList) {
            if (coordinate.i != cur) {
                for (int j = 0; j < (coordinate.i - cur); j++)
                    rowPointer.add(i);
                cur = coordinate.i;
            }
            i++;
        }

        for (int j = 0; j < size; j++)
            rowPointer.add(i);

        return rowPointer;
    }
}
