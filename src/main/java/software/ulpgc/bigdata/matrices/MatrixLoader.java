package software.ulpgc.bigdata.matrices;

import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;

import java.lang.reflect.Type;

public interface MatrixLoader<Type> {
    CoordinateMatrix<Type> loadMatrix(String filePath);
    void saveToFile(CoordinateMatrix<Type> matrix, String fileName);
}
