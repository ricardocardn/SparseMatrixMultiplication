package software.ulpgc.bigdata.matrices.loaders;

import software.ulpgc.bigdata.matrices.MatrixBuilder;
import software.ulpgc.bigdata.matrices.MatrixLoader;
import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class LongMatrixLoader implements MatrixLoader<Long> {
    public CoordinateMatrix<Long> loadMatrix(String filePath) {
        CoordinateMatrixBuilder<Long> matrixBuilder = new CoordinateMatrixBuilder<>(0);

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;
            int num = 0;
            while ((line = br.readLine()) != null) {
                if (line.charAt(0) != '%' && num == 0) {
                    int size = Integer.parseInt(line.split("\\s+")[0]);
                    matrixBuilder = new CoordinateMatrixBuilder<>(size);
                    num++;
                } else if (num != 0) {
                    addCoordinates(matrixBuilder, line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matrixBuilder.get();
    }

    private void addCoordinates(MatrixBuilder<Long> matrixBuilder, String line) {
        String[] coordinates = line.split(" ");
        Random random = new Random();
        long value;
        try {
            value = Long.parseLong(coordinates[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            value = random.nextLong();
        }

        matrixBuilder.set(new Coordinate<>(
                        Integer.parseInt(coordinates[0]) - 1,
                        Integer.parseInt(coordinates[1]) - 1,
                        value
                )
        );
    }

    public void saveToFile(CoordinateMatrix<Long> matrix, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int i = 0;
            for (Coordinate<Long> doubleCoordinate : matrix.get()) {
                bufferedWriter.write(i + " " + doubleCoordinate.i + " " + doubleCoordinate.j + " " + doubleCoordinate.value + "\n");
                i++;
            }

            bufferedWriter.close();
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
