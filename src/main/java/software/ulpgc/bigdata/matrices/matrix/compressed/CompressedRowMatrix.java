package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.Matrix;

import java.util.List;

public class CompressedRowMatrix<Type> implements Matrix<Type> {
    public final int size;
    public final int[] rowPointer;
    public final int[] columns;
    public final List<Type> values;

    public CompressedRowMatrix(int size, int[] rowPointer, int[] columns, List<Type> values) {
        this.size = size;
        this.rowPointer = rowPointer;
        this.columns = columns;
        this.values = values;
    }

    @Override
    public Type get(int i, int j) {
        for (int pos=rowPointer[i]; pos<rowPointer[i+1]; pos++) {
            if (columns[pos] == j)
                return values.get(pos);
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
