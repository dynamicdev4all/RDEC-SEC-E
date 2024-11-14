import java.util.Scanner;

public class DiamondPatternWithReverseRecursion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the height of the diamond (odd number): ");
        int height = scanner.nextInt();

        if (height % 2 == 0) {
            System.out.println("Please enter an odd number.");
        } else {
            printDiamond(height, 0);
            printReverseDiamond(height, 0);
        }

        scanner.close();
    }

    public static void printDiamond(int height, int row) {
        if (row == (height / 2) + 1) {
            return;
        }
        printSpaces((height / 2) - row);
        printStars(2 * row + 1);
        System.out.println();
        printDiamond(height, row + 1);
    }

    public static void printReverseDiamond(int height, int row) {
        if (row == height / 2) {
            return;
        }
        printSpaces(row);
        printStars(2 * ((height / 2) - row) + 1);
        System.out.println();
        printReverseDiamond(height, row + 1);
    }

    public static void printSpaces(int count) {
        if (count == 0) {
            return;
        }
        System.out.print(" ");
        printSpaces(count - 1);
    }

    public static void printStars(int count) {
        if (count == 0) {
            return;
        }
        System.out.print("*");
        if (count > 1) {
            System.out.print(" ");
        }
        printStars(count - 1);
    }
}

