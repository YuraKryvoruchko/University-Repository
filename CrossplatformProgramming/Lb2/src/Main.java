import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DoTask1();
    }
    private static void DoTask1(){
        double a, b, c, x;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input a: ");
        a = scanner.nextDouble();
        System.out.print("Input b: ");
        b = scanner.nextDouble();
        System.out.print("Input c: ");
        c = scanner.nextDouble();
        System.out.print("Input x: ");
        x = scanner.nextDouble();
        scanner.close();

        double result = 0;
        if(a < 0 && x != 0){
            result = a * x * x + b * b * x;
        }
        else if(a > 0 && x == 0){
            result = x - (a / (x - c));
        }
        else {
            result = 1 + (x/c);
        }

        System.out.print("Result: " + result);
    }
    private static void DoTask2(){
        int rotation = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isCorrect = false;
        while (!isCorrect){
            System.out.print("Choose your direction('N', 'E', 'S', 'W'): ");
            char c = Character.toUpperCase(scanner.next().charAt(0));
            switch (c){
                case 'N': isCorrect = true; break;
                case 'E': rotation = 90; isCorrect = true; break;
                case 'S': rotation = 180; isCorrect = true; break;
                case 'W': rotation = 270; isCorrect = true; break;
                default: System.out.println("Incorrect direction! Try again!"); break;
            }
        }
        for(int i = 0; i < 2; i++){
            isCorrect = false;
            while (!isCorrect){
                System.out.printf("Choose command %d(0 - turn right; 1 - turn left; 2 - turn on 180 degrees): ", i+1);
                int number = scanner.nextInt();
                switch (number){
                    case 0: rotation = rotation + 90 == 360 ? 0 : rotation + 90; isCorrect = true; break;
                    case 1: rotation = rotation - 90 < 0 ? 270 : rotation - 90; isCorrect = true; break;
                    case 2: rotation = rotation + 180 >= 360 ? rotation - 180 : rotation + 180; isCorrect = true; break;
                    default: System.out.println("Incorrect command! Try again!"); break;
                }
            }
        }
        scanner.close();
        System.out.print("\nYour new direction: ");
        switch (rotation){
            case 0:     System.out.print('N'); break;
            case 90:    System.out.print('E'); break;
            case 180:   System.out.print('S'); break;
            case 270:   System.out.print('W'); break;
        }
    }
    private static void DoTask3(){
        float area = 0;

        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < 12; i++){
            boolean isCorrect = false;
            float peopleNumber = 0;
            float populationDensity = 0;
            while(!isCorrect){
                System.out.print("People number of " + (i + 1) + " district: ");
                peopleNumber = scanner.nextFloat();
                if(peopleNumber < 0){
                    System.out.println("The number of people cannot be less than 0! Try again!");
                }
                else{
                    isCorrect = true;
                }
            }
            isCorrect = false;
            while(!isCorrect){
                System.out.print("Population density of " + (i + 1) + " district: ");
                populationDensity = scanner.nextFloat();
                if(populationDensity < 0){
                    System.out.println("The number of population density cannot be less than 0! Try again!");
                }
                else{
                    isCorrect = true;
                }
            }

            area += peopleNumber / populationDensity;
        }
        scanner.close();

        System.out.print("\nArea: " + area);
    }
    private static void DoTask4(){
        int N = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the N: ");
        N = scanner.nextInt();
        while(true){
            System.out.print("Input a number: ");
            int number = scanner.nextInt();
            if(number > N){
                System.out.println("The first number greater than N: " + number);
                break;
            }
        }
        scanner.close();
    }
    private static void DoTask5(){
        for(int i = 1; i <= 9; i++){
            for(int j = 1; j <= 9; j++){
                System.out.printf("%d + %d = %d\t", i, j, i+j);
            }
            System.out.println();
        }
    }
}
