import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] array = {"Hello", "Buy", "Good", "Hello", "Good", "Heavy"};
        System.out.println("Strings: " + Arrays.toString(array) + "\n");

        findDuplicateStrings(array);

        int n = 2;
        findStringsWithNEqualPrefix(array, n);
    }

    public static void findDuplicateStrings(String[] array) {
        String[] sortedArray = array.clone();
        java.util.Arrays.sort(sortedArray);

        System.out.println("Number of duplicate strings:");
        int count = 1;
        for (int i = 1; i < sortedArray.length; i++) {
            if (sortedArray[i].equals(sortedArray[i - 1])) {
                count++;
            } else {
                if (count > 1) {
                    System.out.println("String \"" + sortedArray[i - 1] + "\": " + count);
                }
                count = 1;
            }
        }

        if (count > 1) {
            System.out.println("String \"" + sortedArray[sortedArray.length - 1] + "\": " + count);
        }
    }

    public static void findStringsWithNEqualPrefix(String[] array, int n) {
        String[] prefixes = new String[array.length];
        int validPrefixes = 0;

        for (String str : array) {
            if (str.length() >= n) {
                prefixes[validPrefixes++] = str.substring(0, n);
            }
        }

        java.util.Arrays.sort(prefixes, 0, validPrefixes);

        System.out.println("\nNumber of strings which start from " + n + " equal symbols:");
        int count = 1;
        for (int i = 1; i < validPrefixes; i++) {
            if ((prefixes[i].equals(prefixes[i - 1]))) {
                count++;
            } else {
                if (count > 1) {
                    System.out.println("Prefix \"" + prefixes[i - 1] + "\": " + count);
                }
                count = 1;
            }
        }

        if (count > 1) {
            System.out.println("Prefix \"" + prefixes[validPrefixes - 1] + "\": " + count);
        }
    }
}
