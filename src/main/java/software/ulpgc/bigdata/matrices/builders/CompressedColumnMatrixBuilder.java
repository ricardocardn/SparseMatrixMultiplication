package software.ulpgc.bigdata.matrices.builders;

import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedColumnMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CompressedColumnMatrixBuilder<Type> implements MatrixBuilder<Type> {
    private final List<Coordinate<Type>> coordinateList;
    private final int size;

    public CompressedColumnMatrixBuilder(int size) {
        this.coordinateList = new ArrayList<>();
        this.size = size;
    }

    @Override
    public void set(Coordinate<Type> coordinate) {
        coordinateList.add(coordinate);
    }

    @Override
    public CompressedColumnMatrix<Type> get() {
        coordinateList.sort((c1, c2) -> c1.j - c2.j == 0 ? c1.i - c2.i : c1.j - c2.j);
        List<Integer> columnPointer = getColPointer();

        List<Integer> rows = new ArrayList<>();
        List<Type> values = new ArrayList<>();

        for (Coordinate<Type> coordinate : coordinateList) {
            rows.add(coordinate.i);
            values.add(coordinate.value);
        }

        return new CompressedColumnMatrix<>(size, columnPointer, rows, values);
    }

    public List<Integer> getColPointer() {
        List<Integer> columnPointer = new ArrayList<>();

        int i = 0;
        int cur = 0;
        columnPointer.add(i);

        for (Coordinate<Type> coordinate : coordinateList) {
            if (coordinate.j != cur) {
                for (int j = 0; j < (coordinate.j - cur); j++)
                    columnPointer.add(i);
                cur = coordinate.j;
            }
            i++;
        }

        for (int j = 0; j < size; j++)
            columnPointer.add(i);

        return columnPointer;
    }
}
