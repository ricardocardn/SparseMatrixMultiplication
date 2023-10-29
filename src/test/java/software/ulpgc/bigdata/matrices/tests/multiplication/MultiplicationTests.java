package software.ulpgc.bigdata.matrices.tests.multiplication;

import org.junit.Assert;
import org.testng.annotations.Test;
import software.ulpgc.bigdata.matrices.loaders.DoubleMatrixLoader;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.operands.multipliers.DenseDoubleMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseDoubleMatrixMultiplication;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class MultiplicationTests {
    DoubleMatrixLoader matrixLoader;
    CoordinateMatrix<Double> matrixA;
    CoordinateMatrix<Double> matrixB;
    CoordinateMatrix<Double> matrixC;
    CoordinateMatrix<Double> mc2depi;

    SparseDoubleMatrixMultiplication sparseMatrixMultiplication;
    DenseDoubleMatrixMultiplication denseDoubleMatrixMultiplication;

    {
        matrixLoader = new DoubleMatrixLoader();
        matrixA = matrixLoader.loadMatrix("src/test/resources/tests/G67.mtx");
        matrixB = matrixLoader.loadMatrix("src/test/resources/tests/Fashion_MNIST_norm_10NN.mtx");
        matrixC = matrixLoader.loadMatrix("src/test/resources/tests/cryg10000.mtx");
        mc2depi = matrixLoader.loadMatrix("src/test/resources/tests/mc2depi.mtx");
        sparseMatrixMultiplication = new SparseDoubleMatrixMultiplication();
        denseDoubleMatrixMultiplication = new DenseDoubleMatrixMultiplication();
    }

    @Test
    public void largeAssociativeTest() {
        associativeProperty(matrixA, matrixB, matrixC);
    }

    public void associativeProperty(Matrix<Double> matrixA, Matrix<Double> matrixB, Matrix<Double> matrixC) {
        Matrix<Double> resultAB = sparseMatrixMultiplication.multiply(matrixA, matrixB);
        Matrix<Double> resultAB_C = sparseMatrixMultiplication.multiply(resultAB, matrixC);

        Matrix<Double> resultBC = sparseMatrixMultiplication.multiply(matrixB, matrixC);
        Matrix<Double> resultA_BC = sparseMatrixMultiplication.multiply(matrixA, resultBC);

        Assert.assertArrayEquals(((CoordinateMatrix<Double>) resultAB_C).get().toArray(),
                ((CoordinateMatrix<Double>) resultA_BC).get().toArray());
    }
}
