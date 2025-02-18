import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DoTask1();
        DoTask2();
    }

    private static void DoTask1(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Length of array: ");
        int length = scanner.nextInt();
        double[] numbers = new double[length];
        for(int i = 0; i < length; i++){
            System.out.printf("Element №%d: ", i + 1);
            numbers[i] = scanner.nextDouble();
        }
        scanner.close();

        int countOfNegativeNumbers = GetCountOfNegativeNumbers(numbers);
        System.out.println("\nThe count of negative numbers: " + countOfNegativeNumbers);

        double sumOfNumberModulesAfterMinimumModule = GetSumOfNumberModulesAfterMinimumModule(numbers);
        System.out.println("The sum of number modules after minimum module: " + sumOfNumberModulesAfterMinimumModule);

        SquareNegativeElements(numbers);
        System.out.println("The array after squaring negative elements: " + Arrays.toString(numbers));

        Arrays.sort(numbers);
        System.out.print("The sorted array: " + Arrays.toString(numbers));
    }
    private static int GetCountOfNegativeNumbers(double[] numbers){
        int count = 0;
        for (double number : numbers) {
            if (number < 0) {
                count++;
            }
        }
        return count;
    }
    private static double GetSumOfNumberModulesAfterMinimumModule(double[] numbers){
        if(numbers.length == 0){
            return 0;
        }

        double sum = 0;
        int minIndex = 0;
        for(int i = 1; i < numbers.length; i++){
            if(Math.abs(numbers[minIndex]) > Math.abs(numbers[i])){
                minIndex = i;
                sum = 0;
            }
            else{
                sum += Math.abs(numbers[i]);
            }
        }
        return sum;
    }
    private static void SquareNegativeElements(double[] numbers){
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] < 0){
                numbers[i] *= numbers[i];
            }
        }
    }

    private static void DoTask2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Input the number of columns: ");
        int columns = scanner.nextInt();

        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.printf("Element (%d; %d): ", i + 1, j + 1);
                matrix[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        System.out.println("Your matrix:");
        PrintMatrix(matrix);

        int tmp = matrix[0][columns - 1];
        matrix[0][columns - 1] = matrix[rows - 1][0];
        matrix[rows - 1][0] = tmp;
        System.out.println("\nSwapped elements in the top right and bottom left corners:");
        PrintMatrix(matrix);

        tmp = matrix[rows - 1][columns - 1];
        matrix[rows - 1][columns - 1] = matrix[0][0];
        matrix[0][0] = tmp;
        System.out.println("Swapped elements in the bottom right and top left corners:");
        PrintMatrix(matrix);
    }
    private static void PrintMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.printf("%d\t", matrix[i][j]);
            }
            System.out.println();
        }
    }
}