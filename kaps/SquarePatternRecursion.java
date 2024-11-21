import java.util.Scanner;

public class SquarePatternRecursion {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the square: ");
        int n = scanner.nextInt();
        printSquare(n, n);
        scanner.close();
    }

    public static void printSquare(int rows, int cols) {
        if (rows == 0) {
            return;
        }
        printRow(cols);
        printSquare(rows - 1, cols);
    }

    public static void printRow(int cols) {
        if (cols == 0) {
            System.out.println();
            return;
        }
        System.out.print("* ");
        printRow(cols - 1);
    }
}

