package sorting;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataGenerator {

    // random dât
    public static Integer[] generateRandomArray(int size, long seed) {

        Integer[] a = new Integer[size];

        Random random = new Random(seed);

        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(1_000_000);
        }

        return a;
    }

    // save csv
    public static void saveArrayToCSV(Integer[] a, String filePath)
            throws IOException {

        Path path = Path.of(filePath);

        Files.createDirectories(path.getParent());

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {

            for (int i = 0; i < a.length; i++) {
                writer.write(a[i].toString());
                writer.newLine();
            }
        }
    }

    // tạo và lưu
    public static void generateAndSaveDatasets() throws IOException {

        int[] sizes = {
                1000, 10000, 100000, 500000, 1000000
        };

        for (int size : sizes) {

            Integer[] data = generateRandomArray(size, 12345);

            String filePath =
                    "datasets/dataset_" + size + ".csv";

            saveArrayToCSV(data, filePath);

            System.out.println(
                    "Saved: " + filePath
                            + " (" + size + " elements)"
            );
        }

        System.out.println("All datasets generated successfully!");
    }

    public static Integer[] loadArrayFromCSV(String filePath)
            throws IOException {

        List<Integer> values = new ArrayList<>();

        try (BufferedReader reader =
                     Files.newBufferedReader(Path.of(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {
                values.add(Integer.parseInt(line.trim()));
            }
        }

        return values.toArray(new Integer[0]);
    }

    // Chạy riêng
    public static void main(String[] args) throws IOException {
        Integer[] data = loadArrayFromCSV(
                "datasets/dataset_1000.csv"
        );

        System.out.println("Số phần tử: " + data.length);
        System.out.println("5 phần tử đầu tiên:");

        for (int i = 0; i < 5; i++) {
            System.out.println(data[i]);
        }

        generateAndSaveDatasets();
    }

}

