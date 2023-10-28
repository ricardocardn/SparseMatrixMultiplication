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
        int[] rowPointer = getRowPointer();
        int[] cols = new int[coordinateList.size()];
        List<Type> values = new ArrayList<>();

        int i = 0;
        for (Coordinate<Type> coordinate : coordinateList) {
            cols[i] = coordinate.j;
            values.add(coordinate.value);

            i++;
        }

        return new CompressedRowMatrix<>(size, rowPointer, cols, values);
    }

    public int[] getRowPointer() {
        int[] rowPointer = new int[size + 1];
        int i = 0;
        int cur = 0;
        rowPointer[0] = 0;

        for (Coordinate<Type> coordinate : coordinateList) {
            if (coordinate.i != cur) {
                for (int j = 0; j < (coordinate.i - cur); j++)
                    rowPointer[cur + j + 1] = i;
                cur = coordinate.i;
            }
            i++;
        }

        for (int j = cur; j < size; j++)
            rowPointer[j + 1] = i;

        return rowPointer;
    }
}
