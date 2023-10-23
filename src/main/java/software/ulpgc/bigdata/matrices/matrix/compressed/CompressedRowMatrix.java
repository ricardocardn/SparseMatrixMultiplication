package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.Matrix;

import java.util.List;

public class CompressedRowMatrix<Type> implements Matrix<Type> {
    private final int size;
    private final List<Integer> rowPointer;
    private final List<Integer> columns;
    private final List<Type> values;

    public CompressedRowMatrix(int size, List<Integer> rowPointer, List<Integer> columns, List<Type> values) {
        this.size = size;
        this.rowPointer = rowPointer;
        this.columns = columns;
        this.values = values;
    }

    @Override
    public Type get(int i, int j) {
        for (int pos=rowPointer.get(i); pos<rowPointer.get(i+1); pos++) {
            if (columns.get(pos) == j)
                return values.get(pos);
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public List<Integer> getRowPointer() {
        return rowPointer;
    }

    public List<Integer> getColumns() {
        return columns;
    }

    public List<Type> getValues() {
        return values;
    }
}
