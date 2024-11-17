public class PlusPattern {
    public static void main(String[] args) {
        int n = 5; // Size of the pattern
        printPlusPattern(n, 0, 0);
    }

    public static void printPlusPattern(int n, int row, int col) {
        if (row == n) {
            return;
        }
        
        if (col == n) {
            System.out.println();
            printPlusPattern(n, row + 1, 0);
            return;
        }

        if (col == n / 2 || row == n / 2) {
            System.out.print("+");
        } else {
            System.out.print(" ");
        }

        printPlusPattern(n, row, col + 1);
    }
}


