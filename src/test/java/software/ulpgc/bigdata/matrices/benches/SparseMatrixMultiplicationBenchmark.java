package software.ulpgc.bigdata.matrices.benches;

import org.openjdk.jmh.annotations.*;
import software.ulpgc.bigdata.matrices.MatrixLoader;
import software.ulpgc.bigdata.matrices.loaders.DoubleMatrixLoader;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseDoubleMatrixMultiplication;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class SparseMatrixMultiplicationBenchmark {
    @Param({"dw1024", "delaunay_n12", "dw4096", "delaunay_n14", "delaunay_n16", "delaunay_n17"})
    public String name;
    MatrixLoader<Double> matrixLoader = new DoubleMatrixLoader();
    SparseDoubleMatrixMultiplication sparseDoubleMatrixMultiplication = new SparseDoubleMatrixMultiplication();

    CoordinateMatrix<Double> matrixA;

    @Setup
    public void setup() {
        matrixA = matrixLoader.loadMatrix("src/test/resources/benchmarks/" + name + ".mtx");
    }

    @Benchmark
    public void multiplication() {
        sparseDoubleMatrixMultiplication.multiply(matrixA, matrixA);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}










