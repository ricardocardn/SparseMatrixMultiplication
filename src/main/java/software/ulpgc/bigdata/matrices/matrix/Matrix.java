package software.ulpgc.bigdata.matrices.matrix;

public interface Matrix<Type> {
    Type get(int i, int j);
    int size();
}
