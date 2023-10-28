package software.ulpgc.bigdata.matrices.tests.multiplication;

import org.junit.Assert;
import org.testng.annotations.Test;
import software.ulpgc.bigdata.matrices.DoubleMatrixLoader;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseDoubleMatrixMultiplication;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class MultiplicationTests {
    DoubleMatrixLoader matrixLoader;
    CoordinateMatrix<Double> matrixA;
    CoordinateMatrix<Double> matrixB;
    CoordinateMatrix<Double> matrixC;
    CoordinateMatrix<Double> matrixD;
    CoordinateMatrix<Double> matrixE;
    CoordinateMatrix<Double> matrixF;
    CoordinateMatrix<Double> mc2depi;

    SparseDoubleMatrixMultiplication sparseMatrixMultiplication;

    {
        matrixLoader = new DoubleMatrixLoader();
        matrixA = matrixLoader.loadMatrix("src/test/resources/G67.mtx");
        matrixB = matrixLoader.loadMatrix("src/test/resources/Fashion_MNIST_norm_10NN.mtx");
        matrixC = matrixLoader.loadMatrix("src/test/resources/cryg10000.mtx");
        matrixD = matrixLoader.loadMatrix("src/test/resources/Example.mtx");
        matrixE = matrixLoader.loadMatrix("src/test/resources/Example2.mtx");
        matrixF = matrixLoader.loadMatrix("src/test/resources/Example3.mtx");
        mc2depi = matrixLoader.loadMatrix("src/test/resources/mc2depi.mtx");
        sparseMatrixMultiplication = new SparseDoubleMatrixMultiplication();
    }

    @Test
    public void squareMatrix() {
        long start = System.currentTimeMillis();
        sparseMatrixMultiplication.multiply(mc2depi, mc2depi);
        long end = System.currentTimeMillis();

        System.out.println("Time taken for size " + mc2depi.size() + ": " + (end-start)/1000.);
    }

    @Test
    public void smallAssociativeTest() {
        associativeProperty(matrixD, matrixE, matrixF);
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
