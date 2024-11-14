import java.util.Scanner;

public class RightTrianglePatternRecursion {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the height of the right triangle: ");
        int n = scanner.nextInt();
        printRightTriangle(n, 1);
        scanner.close();
    }

    public static void printRightTriangle(int height, int row) {
        if (row > height) {
            return;
        }
        printStars(row);
        printRightTriangle(height, row + 1);
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

