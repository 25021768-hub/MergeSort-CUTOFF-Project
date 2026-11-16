
package sorting;

import java.util.Arrays;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Benchmark {

    private static final int[] CUTOFFS = {0, 7, 10};

    private static final int[] SIZES = {
            1_000,
            10_000,
            100_000,
            500_000,
            1_000_000
    };

    private static final int TRIALS = 20;
    private static final int WARMUP_ROUNDS = 5;


    public static void run() throws IOException {

        System.out.println("Starting Merge Sort Benchmark...");
        System.out.println("Number of trials: " + TRIALS);

        double[][] results = new double[SIZES.length][CUTOFFS.length];

        warmUp();

        for (int s = 0; s < SIZES.length; s++) {

            int size = SIZES[s];

            Integer[] original = loadDataset(size);

            long[][] times = new long[CUTOFFS.length][TRIALS];

            for (int trial = 0; trial < TRIALS; trial++) {

                int start = trial % CUTOFFS.length;

                for (int i = 0; i < CUTOFFS.length; i++) {

                    int index = (start + i) % CUTOFFS.length;
                    int cutoff = CUTOFFS[index];

                    Integer[] copy = original.clone();

                    long startTime = System.nanoTime();

                    MergeSort.sort(copy, cutoff);

                    long endTime = System.nanoTime();

                    times[index][trial] = endTime - startTime;

                    if (!isSorted(copy)) {
                        throw new IllegalStateException(
                                "Sorting failed with cutoff = " + cutoff
                        );
                    }
                }
            }

            System.out.println("Array size: " + size);
            System.out.println("--------------------------------");

            for (int i = 0; i < CUTOFFS.length; i++) {

                double result = median(times[i]);

                results[s][i] = result;

                System.out.printf(
                        "CUTOFF = %d: %.4f ms%n",
                        CUTOFFS[i],
                        result
                );
            }

            System.out.println();
        }

        saveResults(results);

    }



    private static void warmUp() throws IOException {

        System.out.println("Warming up JVM...");

        for (int size : SIZES) {

            Integer[] data = loadDataset(size);
            for (int round = 0; round < WARMUP_ROUNDS; round++) {

                for (int cutoff : CUTOFFS) {

                    Integer[] copy = data.clone();

                    MergeSort.sort(copy, cutoff);
                }
            }

            System.out.println("Warm-up completed for size: " + size);
        }

        System.out.println("All warm-up completed.\n");
    }


    private static boolean isSorted(Comparable[] a) {

        for (int i = 1; i < a.length; i++) {

            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }

        return true;
    }

    private static double median(long[] values) {

        long[] sorted = values.clone();

        Arrays.sort(sorted);

        int n = sorted.length;

        if (n % 2 == 1) {
            return sorted[n / 2] / 1_000_000.0;
        }

        return (sorted[n / 2 - 1] + sorted[n / 2])
                / 2_000_000.0;
    }

    private static void saveResults(double[][] results) {

        Path directory = Path.of("results");
        Path file = directory.resolve("benchmark_results.csv");

        try {

            Files.createDirectories(directory);

            try (BufferedWriter writer = Files.newBufferedWriter(file)) {

                writer.write("Array Size,CUTOFF 0 (ms),CUTOFF 7 (ms),CUTOFF 10 (ms)");
                writer.newLine();

                for (int i = 0; i < SIZES.length; i++) {

                    writer.write(
                            SIZES[i] + "," +
                                    results[i][0] + "," +
                                    results[i][1] + "," +
                                    results[i][2]
                    );

                    writer.newLine();
                }
            }

            System.out.println("Results saved to: " + file.toAbsolutePath());

        } catch (IOException e) {

            System.out.println("Error saving results: " + e.getMessage());
        }
    }
    private static Integer[] loadDataset(int size)
            throws IOException {

        String filePath = "datasets/dataset_" + size + ".csv";

        Integer[] data = DataGenerator.loadArrayFromCSV(filePath);

        if (data.length != size) {
            throw new IllegalStateException(
                    "Dataset size mismatch: expected " + size
                            + ", got " + data.length
            );
        }

        return data;
    }

}
