import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n;
        double r;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input number of angles: ");
        n = scanner.nextInt();
        System.out.print("Input radius: ");
        r = scanner.nextDouble();
        scanner.close();

        double perimeter = n * 2 * r * Math.tan(Math.PI / n);
        double area = n * r * r * Math.tan(Math.PI / n);

        System.out.println("\nPerimeter " + n + "-angle with radius " + r +": " + perimeter);
        System.out.println("Area " + n + "-angle: with radius " + r +": " + area);
    }
}