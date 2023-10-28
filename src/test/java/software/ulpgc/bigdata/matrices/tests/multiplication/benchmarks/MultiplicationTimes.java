package software.ulpgc.bigdata.matrices.tests.multiplication.benchmarks;

import org.testng.annotations.Test;
import software.ulpgc.bigdata.matrices.DoubleMatrixLoader;
import software.ulpgc.bigdata.matrices.matrix.Matrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseDoubleMatrixMultiplication;

public class MultiplicationTimes {
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
        matrixA = matrixLoader.loadMatrix("src/test/resources/benchmarks/delaunay_n12.mtx");
        matrixB = matrixLoader.loadMatrix("src/test/resources/benchmarks/delaunay_n14.mtx");
        matrixC = matrixLoader.loadMatrix("src/test/resources/benchmarks/delaunay_n16.mtx");
        matrixD = matrixLoader.loadMatrix("src/test/resources/benchmarks/delaunay_n17.mtx");
        matrixE = matrixLoader.loadMatrix("src/test/resources/benchmarks/dw1024.mtx");
        matrixF = matrixLoader.loadMatrix("src/test/resources/benchmarks/dw4096.mtx");
        mc2depi = matrixLoader.loadMatrix("src/test/resources/tests/mc2depi.mtx");
        sparseMatrixMultiplication = new SparseDoubleMatrixMultiplication();
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
        sparseMatrixMultiplication.multiply(matrix, matrix);
        long end = System.currentTimeMillis();

        System.out.println("Time taken for size " + matrix.size() + ": " + (end-start)/1000.);
    }
}
