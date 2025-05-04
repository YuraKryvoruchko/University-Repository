import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the directory path: ");
        String directoryPath = scanner.nextLine();
        scanner.close();

        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("The specified directory does not exist or is not a directory!");
            return;
        }

        List<String> results = countLinesInFiles(directory);

        System.out.println("\nResults:");
        for (String result : results) {
            System.out.println(result);
        }

        String outputFile = "results.txt";
        saveResultsToFile(results, outputFile);
        System.out.println("\nResults saved to file: " + outputFile);
    }

    private static List<String> countLinesInFiles(File directory) {
        List<String> results = new ArrayList<>();

        try {
            File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
            if (files == null) {
                results.add("Error: Unable to access directory contents");
                return results;
            }

            for (File file : files) {
                try {
                    long lineCount = Files.lines(file.toPath()).count();
                    results.add("File: " + file.getName() + ", Lines: " + lineCount);
                } catch (IOException e) {
                    results.add("File: " + file.getName() + ", Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            results.add("Error accessing directory: " + e.getMessage());
        }

        return results;
    }

    private static void saveResultsToFile(List<String> results, String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String result : results) {
                writer.write(result);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}