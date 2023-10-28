package software.ulpgc.bigdata.matrices.tests.multiplication.benchmarks;

import org.testng.annotations.Test;
import software.ulpgc.bigdata.matrices.DoubleMatrixLoader;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.operands.multipliers.DenseDoubleMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseDoubleMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2Dense;

public class DenseMultiplicationTimes {
    DoubleMatrixLoader matrixLoader;
    Transform2Dense transform2Dense;

    CoordinateMatrix<Double> matrixA;
    CoordinateMatrix<Double> matrixB;
    CoordinateMatrix<Double> matrixC;
    CoordinateMatrix<Double> matrixD;
    CoordinateMatrix<Double> matrixE;
    CoordinateMatrix<Double> matrixF;
    CoordinateMatrix<Double> mc2depi;

    DenseDoubleMatrixMultiplication denseDoubleMatrixMultiplication;

    {
        matrixLoader = new DoubleMatrixLoader();
        transform2Dense = new Transform2Dense<>();

        matrixA = matrixLoader.loadMatrix("src/test/resources/benchmarks/delaunay_n12.mtx");
        matrixB = matrixLoader.loadMatrix("src/test/resources/benchmarks/delaunay_n14.mtx");
        matrixC = matrixLoader.loadMatrix("src/test/resources/benchmarks/delaunay_n16.mtx");
        matrixD = matrixLoader.loadMatrix("src/test/resources/benchmarks/delaunay_n17.mtx");
        matrixE = matrixLoader.loadMatrix("src/test/resources/benchmarks/dw1024.mtx");
        matrixF = matrixLoader.loadMatrix("src/test/resources/benchmarks/dw4096.mtx");
        mc2depi = matrixLoader.loadMatrix("src/test/resources/tests/mc2depi.mtx");

        denseDoubleMatrixMultiplication = new DenseDoubleMatrixMultiplication();
    }

    @Test
    public void matrixA() {
        System.out.println("Matrix A of order " + matrixA.size());
        squareMatrix(matrixA);
    }

    @Test
    public void matrixB() {
        System.out.println("Matrix B of order " + matrixB.size());
        squareMatrix(matrixB);
    }

    @Test
    public void matrixC() {
        System.out.println("Matrix C of order " + matrixC.size());
        squareMatrix(matrixC);
    }

    @Test
    public void matrixD() {
        System.out.println("Matrix A of order " + matrixD.size());
        squareMatrix(matrixD);
    }

    @Test
    public void matrixE() {
        System.out.println("Matrix A of order " + matrixE.size());
        squareMatrix(matrixE);
    }

    @Test
    public void matrixF() {
        System.out.println("Matrix A of order " + matrixF.size());
        squareMatrix(matrixF);
    }

    public void squareMatrix(Matrix<Double> matrix) {
        long start = System.currentTimeMillis();
        denseDoubleMatrixMultiplication.multiply(
                transform2Dense.execute(matrix),
                transform2Dense.execute(matrix));
        long end = System.currentTimeMillis();

        System.out.println("Time taken for size " + matrix.size() + ": " + (end-start)/1000.);
    }
}
