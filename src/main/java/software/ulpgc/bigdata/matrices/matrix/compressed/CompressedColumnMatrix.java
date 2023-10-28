package software.ulpgc.bigdata.matrices.matrix.compressed;

import software.ulpgc.bigdata.matrices.matrix.Matrix;

import java.util.List;

public class CompressedColumnMatrix<Type> implements Matrix<Type> {
    public final int size;
    public final int[] colPointer;
    public final int[] rows;
    public final List<Type> values;

    public CompressedColumnMatrix(int size, int[] colPointer, int[] rows, List<Type> values) {
        this.size = size;
        this.colPointer = colPointer;
        this.rows = rows;
        this.values = values;
    }

    @Override
    public Type get(int i, int j) {
        for (int pos=colPointer[j]; pos<colPointer[j+1]; pos++) {
            if (rows[i] == i)
                return values.get(pos);
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public List<Type> getValues() {
        return values;
    }
}
