import java.util.Scanner;

public class HollowSquarePatternRecursion {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the hollow square: ");
        int n = scanner.nextInt();
        printHollowSquare(n, 0);
        scanner.close();
    }

    public static void printHollowSquare(int n, int row) {
        if (row == n) {
            return;
        }
        printHollowRow(n, row, 0);
        printHollowSquare(n, row + 1);
    }

    public static void printHollowRow(int n, int row, int col) {
        if (col == n) {
            System.out.println();
            return;
        }
        if (row == 0 || row == n - 1 || col == 0 || col == n - 1) {
            System.out.print("* ");
        } else {
            System.out.print("  ");
        }
        printHollowRow(n, row, col + 1);
    }
}
