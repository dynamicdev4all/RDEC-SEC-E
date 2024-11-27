public class EightPattern {
    public static void main(String[] args) {
        int size = 7; // Size of the pattern
        printEightPattern(size, 0, 0);
    }

    public static void printEightPattern(int size, int row, int col) {
        if (row == size) {
            return;
        }

        if (col == size) {
            System.out.println();
            printEightPattern(size, row + 1, 0);
            return;
        }

        if ((row == 0 || row == size - 1 || row == size / 2) || (col == 0 || col == size - 1)) {
            System.out.print("*");
        } else {
            System.out.print(" ");
        }

        printEightPattern(size, row, col + 1);
    }
}
