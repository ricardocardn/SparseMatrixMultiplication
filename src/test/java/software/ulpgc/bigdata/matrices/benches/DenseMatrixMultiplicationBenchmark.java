package software.ulpgc.bigdata.matrices.benches;

import org.openjdk.jmh.annotations.*;
import software.ulpgc.bigdata.matrices.MatrixLoader;
import software.ulpgc.bigdata.matrices.loaders.DoubleMatrixLoader;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.operands.MatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.multipliers.DenseDoubleMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseDoubleMatrixMultiplication;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(2)
@State(Scope.Benchmark)
public class DenseMatrixMultiplicationBenchmark {
    @Param({"dw1024"})
    public String name;
    MatrixLoader<Double> matrixLoader = new DoubleMatrixLoader();
    MatrixMultiplication<Double> denseDoubleMatrixMultiplication = new DenseDoubleMatrixMultiplication();

    CoordinateMatrix<Double> matrixA;

    @Setup
    public void setup() {
        matrixA = matrixLoader.loadMatrix("src/test/resources/benchmarks/" + name + ".mtx");
    }

    @Benchmark
    public void multiplication() {
        denseDoubleMatrixMultiplication.multiply(matrixA, matrixA);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
}










