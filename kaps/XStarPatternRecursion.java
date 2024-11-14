import java.util.Scanner;

public class XStarPatternRecursion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the X pattern (odd number): ");
        int size = scanner.nextInt();

        if (size % 2 == 0) {
            System.out.println("Please enter an odd number.");
        } else {
            printXPattern(size, 0);
        }

        scanner.close();
    }

    public static void printXPattern(int size, int row) {
        if (row == size) {
            return;
        }
        printXRow(size, row);
        printXPattern(size, row + 1);
    }

    public static void printXRow(int size, int row) {
        for (int col = 0; col < size; col++) {
            if (col == row || col == size - row - 1) {
                System.out.print("* ");
            } else {
                System.out.print("  ");
            }
        }
        System.out.println();
    }
}

