package software.ulpgc.bigdata.matrices.tests.multiplication;

import org.testng.annotations.Test;
import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.MatrixLoader;
import software.ulpgc.bigdata.matrices.Operator;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedColMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.LongCoordinate;
import software.ulpgc.bigdata.matrices.operands.multipliers.SparseMatrixMultiplication;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CCS;
import software.ulpgc.bigdata.matrices.operands.transformers.Transform2CRS;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class MultiplicationTests {
    /*CompressedMatrix matrixA = MatrixLoader.loadMatrix("src/test/resources/Tina_AskCal.mtx");
    CompressedMatrix matrixB = MatrixLoader.loadMatrix("src/test/resources/Tina_AskCog.mtx");
    CompressedMatrix matrixC = MatrixLoader.loadMatrix("src/test/resources/Tina_DisCal.mtx");*/
    CompressedMatrix matrixD = MatrixLoader.loadMatrix("src/test/resources/Example.mtx");
    CompressedMatrix matrixE = MatrixLoader.loadMatrix("src/test/resources/Example2.mtx");
    CompressedMatrix matrixF = MatrixLoader.loadMatrix("src/test/resources/Example3.mtx");

    SparseMatrixMultiplication sparseMatrixMultiplication = new SparseMatrixMultiplication();


    @Test
    public void squareTest() {
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

    /*@Test
    public void associativePropertyTest() {
        CompressedMatrix resultAB = sparseMatrixMultiplication.multiply(
                (new Transform2CRS()).execute(matrixA),
                (new Transform2CCS()).execute(matrixB));

        CompressedMatrix resultAB_C = sparseMatrixMultiplication.multiply(
                (new Transform2CRS()).execute(resultAB),
                (new Transform2CCS()).execute(matrixC));

        System.out.println(resultAB_C.get());

        CompressedMatrix resultBC = sparseMatrixMultiplication.multiply(
                (new Transform2CRS()).execute(matrixB),
                (new Transform2CCS()).execute(matrixC));

        CompressedMatrix resultA_BC = sparseMatrixMultiplication.multiply(
                (new Transform2CRS()).execute(matrixA),
                (new Transform2CCS()).execute(resultBC));

        System.out.println(resultA_BC.get());


        //assertArrayEquals(resultA_BC.get().toArray(),resultAB_C.get().toArray());
    }*/
}
