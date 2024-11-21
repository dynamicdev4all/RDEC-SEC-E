import java.util.Scanner;

public class RightAlignedParallelogramPatternRecursion {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the height of the parallelogram: ");
        int height = scanner.nextInt();
        System.out.print("Enter the width of the parallelogram: ");
        int width = scanner.nextInt();
        printParallelogram(height, width, 0);
        scanner.close();
    }

    public static void printParallelogram(int height, int width, int row) {
        if (row == height) {
            return;
        }
        printSpaces(height - row - 1);
        printStars(width);
        printParallelogram(height, width, row + 1);
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
            System.out.println();
            return;
        }
        System.out.print("* ");
        printStars(count - 1);
    }
}

