package software.ulpgc.bigdata.matrices;

import software.ulpgc.bigdata.matrices.builders.CoordinateMatrixBuilder;
import software.ulpgc.bigdata.matrices.matrix.compressed.CoordinateMatrix;
import software.ulpgc.bigdata.matrices.matrix.compressed.coordinates.Coordinate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.Random;

public class DoubleMatrixLoader {
    public CoordinateMatrix<Double> loadMatrix(String filePath) {
        CoordinateMatrixBuilder<Double> matrixBuilder = new CoordinateMatrixBuilder<>(0);

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

    private void addCoordinates(MatrixBuilder<Double> matrixBuilder, String line) {
        String[] coordinates = line.split(" ");
        Random random = new Random();
        double value;
        try {
            value = Double.parseDouble(coordinates[2]);
        } catch (ArrayIndexOutOfBoundsException e) {
            value = random.nextDouble();
        }

        matrixBuilder.set(new Coordinate<Double>(
                        Integer.parseInt(coordinates[0]),
                        Integer.parseInt(coordinates[1]),
                        value
                )
        );
    }

    public void saveToFile(CoordinateMatrix<Double> matrix, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int i = 0;
            for (Coordinate<Double> doubleCoordinate : matrix.get()) {
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
