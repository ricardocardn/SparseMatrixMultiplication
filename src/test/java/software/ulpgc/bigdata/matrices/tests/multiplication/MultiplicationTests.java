package software.ulpgc.bigdata.matrices.tests.multiplication;

import org.testng.annotations.Test;
import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.MatrixLoader;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CCS;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CRS;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class MultiplicationTests {
    CompressedMatrix matrixD;
    CompressedMatrix matrixE;
    CompressedMatrix matrixF;

    SparseMatrixMultiplication sparseMatrixMultiplication;

    {
        matrixD = MatrixLoader.loadMatrix("src/test/resources/Example.mtx");
        matrixE = MatrixLoader.loadMatrix("src/test/resources/Example2.mtx");
        matrixF = MatrixLoader.loadMatrix("src/test/resources/Example3.mtx");
        sparseMatrixMultiplication = new SparseMatrixMultiplication();
    }


    @Test
    public void associativePropertyTest() {
        System.out.println(matrixD.get());

        CompressedMatrix resultDE = sparseMatrixMultiplication.multiply(
                (new Transform2CRS()).execute(matrixD),
                (new Transform2CCS()).execute(matrixE));

        CompressedMatrix resultDE_F = sparseMatrixMultiplication.multiply(
                (new Transform2CRS()).execute(resultDE),
                (new Transform2CCS()).execute(matrixF));

        CompressedMatrix resultEF = sparseMatrixMultiplication.multiply(
                (new Transform2CRS()).execute(matrixE),
                (new Transform2CCS()).execute(matrixF));

        CompressedMatrix resultD_EF = sparseMatrixMultiplication.multiply(
                (new Transform2CRS()).execute(matrixD),
                (new Transform2CCS()).execute(resultEF));

        System.out.println(resultD_EF.get());
        System.out.println(resultDE_F.get());

        assert Arrays.deepEquals(resultD_EF.get().toArray(), resultDE_F.get().toArray());
    }
}
