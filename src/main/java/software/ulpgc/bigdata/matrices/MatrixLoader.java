package software.ulpgc.bigdata.matrices;

import software.ulpgc.bigdata.matrices.CompressedMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CompressedRowMatrixBuilder;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.CompressedMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CompressedRowMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.DoubleCoordinate;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.LongCoordinate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class MatrixLoader {
    public static CompressedMatrix loadMatrix(String filePath) {
        CompressedMatrixBuilder compressedMatrixBuilder = new CoordinateMatrixBuilder(0);

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            int num = 0;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) != '%' && num == 0) {
                    int size = Integer.parseInt(line.split("\\s+")[0]);
                    compressedMatrixBuilder = new CoordinateMatrixBuilder(size);
                    num++;
                } else if (num != 0) {
                    addCoordinates(compressedMatrixBuilder, line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return compressedMatrixBuilder.getMatrix();
    }

    private static void addCoordinates(CompressedMatrixBuilder compressedMatrixBuilder, String line) {
        String[] coordinates = line.split(" ");
        compressedMatrixBuilder.set(
                new DoubleCoordinate(
                        Integer.parseInt(coordinates[0]),
                        Integer.parseInt(coordinates[1]),
                        Double.parseDouble(coordinates[2])
                )
        );
    }
}
