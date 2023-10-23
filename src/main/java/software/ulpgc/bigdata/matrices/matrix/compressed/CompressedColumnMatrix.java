package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.Matrix;

import java.util.List;

public class CompressedColumnMatrix<Type> implements Matrix<Type> {
    private final int size;
    private final List<Integer> colPointer;
    private final List<Integer> rows;
    private final List<Type> values;

    public CompressedColumnMatrix(int size, List<Integer> colPointer, List<Integer> rows, List<Type> values) {
        this.size = size;
        this.colPointer = colPointer;
        this.rows = rows;
        this.values = values;
    }

    @Override
    public Type get(int i, int j) {
        for (int pos=colPointer.get(j); pos<colPointer.get(j+1); pos++) {
            if (rows.get(i) == i)
                return values.get(pos);
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public List<Integer> getColPointer() {
        return colPointer;
    }

    public List<Integer> getRows() {
        return rows;
    }

    public List<Type> getValues() {
        return values;
    }
}
