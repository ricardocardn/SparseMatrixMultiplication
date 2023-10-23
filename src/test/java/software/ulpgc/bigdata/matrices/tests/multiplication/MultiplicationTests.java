package software.ulpgc.bigdata.matrices.tests.multiplication;

import org.testng.annotations.Test;
import software.ulpgc.bigdata.matrices.MatrixLoader;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CCS;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CRS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class MultiplicationTests {
    MatrixLoader<Double> matrixLoader;
    CoordinateMatrix<Double> matrixA;
    CoordinateMatrix<Double> matrixB;
    CoordinateMatrix<Double> matrixC;
    CoordinateMatrix<Double> matrixD;
    CoordinateMatrix<Double> matrixE;
    CoordinateMatrix<Double> matrixF;
    CoordinateMatrix<Double> mc2depi;

    SparseMatrixMultiplication<Double> sparseMatrixMultiplication;

    {
        matrixLoader = new MatrixLoader<>();
        matrixA = matrixLoader.loadMatrix("src/test/resources/G67.mtx");
        matrixB = matrixLoader.loadMatrix("src/test/resources/Fashion_MNIST_norm_10NN.mtx");
        matrixC = matrixLoader.loadMatrix("src/test/resources/cryg10000.mtx");
        matrixD = matrixLoader.loadMatrix("src/test/resources/Example.mtx");
        matrixE = matrixLoader.loadMatrix("src/test/resources/Example2.mtx");
        matrixF = matrixLoader.loadMatrix("src/test/resources/Example3.mtx");
        mc2depi = matrixLoader.loadMatrix("src/test/resources/mc2depi.mtx");
        sparseMatrixMultiplication = new SparseMatrixMultiplication<>();
    }

    @Test
    public void squareMatrix() {
        long start = System.currentTimeMillis();
        sparseMatrixMultiplication.multiply(
                (new Transform2CRS<Double>()).execute(mc2depi),
                (new Transform2CCS<Double>()).execute(mc2depi)
        );
        long end = System.currentTimeMillis();

        System.out.println("Time taken for size " + mc2depi.size() + ": " + (end-start)/1000.);
    }

    @Test
    public void smallAssociativeTest() {
        //assert associativeProperty(matrixD, matrixE, matrixF);
    }

    @Test
    public void largeAssociativeTest() {
        assert associativeProperty(matrixA, matrixB, matrixC);
    }


    public boolean associativeProperty(Matrix<Double> matrixA, Matrix<Double> matrixB, Matrix<Double> matrixC) {
        Matrix<Double> resultDE = sparseMatrixMultiplication.multiply(
                (new Transform2CRS<Double>()).execute(matrixA),
                (new Transform2CCS<Double>()).execute(matrixB));

        Matrix<Double> resultDE_F = sparseMatrixMultiplication.multiply(
                (new Transform2CRS<Double>()).execute(resultDE),
                (new Transform2CCS<Double>()).execute(matrixC));

        Matrix<Double> resultEF = sparseMatrixMultiplication.multiply(
                (new Transform2CRS<Double>()).execute(matrixB),
                (new Transform2CCS<Double>()).execute(matrixC));

        Matrix<Double> resultD_EF = sparseMatrixMultiplication.multiply(
                (new Transform2CRS<Double>()).execute(matrixA),
                (new Transform2CCS<Double>()).execute(resultEF));

        matrixLoader.saveToFile((CoordinateMatrix<Double>) resultD_EF, "resultD_EF");
        matrixLoader.saveToFile((CoordinateMatrix<Double>) resultDE_F, "resultDE_F");

        return Arrays.deepEquals(((CoordinateMatrix<Double>) resultD_EF).get().toArray(),
                ((CoordinateMatrix<Double>) resultDE_F).get().toArray());
    }
}
