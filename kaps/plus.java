import java.util.Scanner;

public class plus {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the plus pattern (odd number): ");
        int size = scanner.nextInt();
        
        if (size % 2 == 0) {
            System.out.println("Please enter an odd number.");
        } else {
            printPlusPattern(size, 0);
        }
        
        scanner.close();
    }

    public static void printPlusPattern(int size, int row) {
        if (row == size) {
            return;
        }
        printRow(size, row);
        printPlusPattern(size, row + 1);
    }

    public static void printRow(int size, int row) {
        for (int col = 0; col < size; col++) {
            if (col == size / 2 || row == size / 2) {
                System.out.print("+ ");
            } else {
                System.out.print("  ");
            }
        }
        System.out.println();
    }
}

