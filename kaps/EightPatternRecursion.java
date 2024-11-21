import java.util.Scanner;

public class EightPatternRecursion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the '8' pattern (odd number, preferably 5): ");
        int size = scanner.nextInt();

        if (size % 2 == 0 || size < 3) {
            System.out.println("Please enter an odd number greater than or equal to 3.");
        } else {
            printEightPattern(size, 0);
        }

        scanner.close();
    }

    public static void printEightPattern(int size, int row) {
        if (row == size) {
            return;
        }
        printEightRow(size, row);
        printEightPattern(size, row + 1);
    }

    public static void printEightRow(int size, int row) {
        for (int col = 0; col < size; col++) {
            if (row == 0 || row == size - 1 || row == size / 2 || col == 0 || col == size - 1) {
                System.out.print("* ");
            } else {
                System.out.print("  ");
            }
        }
        System.out.println();
    }
}
