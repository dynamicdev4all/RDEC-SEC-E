import java.util.Scanner;

public class ReverseRightTrianglePatternRecursion {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the height of the right triangle: ");
        int n = scanner.nextInt();
        printReverseRightTriangle(n, n);
        scanner.close();
    }

    public static void printReverseRightTriangle(int height, int row) {
        if (row == 0) {
            return;
        }
        printStars(row);
        printReverseRightTriangle(height, row - 1);
    }

    public static void printStars(int count) {
        if (count == 0) {
            System.out.println();
            return;
        }
        System.out.print("* ");
        printStars(count - 1);
    }
}
