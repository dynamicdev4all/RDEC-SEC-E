import java.util.Scanner;

public class LeftArrowPatternWithReverseRecursion {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the height of the arrow: ");
        int height = scanner.nextInt();
        printLeftArrow(height, 0);
        printReverseLeftArrow(height, 0);
        scanner.close();
    }

    public static void printLeftArrow(int height, int row) {
        if (row == height) {
            return;
        }
        printStars(height - row);
        printLeftArrow(height, row + 1);
    }

    public static void printReverseLeftArrow(int height, int row) {
        if (row == height) {
            return;
        }
        printStars(row + 1);
        printReverseLeftArrow(height, row + 1);
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