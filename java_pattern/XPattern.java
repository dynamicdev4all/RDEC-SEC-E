public class XPattern {
    public static void main(String[] args) {
        int size = 7; // Size of the pattern
        printXPattern(size, 0, 0);
    }

    public static void printXPattern(int size, int row, int col) {
        if (row == size) {
            return;
        }

        if (col == size) {
            System.out.println();
            printXPattern(size, row + 1, 0);
            return;
        }

        if (col == row || col == (size - row - 1)) {
            System.out.print("*");
        } else {
            System.out.print(" ");
        }

        printXPattern(size, row, col + 1);
    }
}

