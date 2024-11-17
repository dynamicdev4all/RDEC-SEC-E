public class DiamondStarPattern {
    public static void main(String[] args) {
        int n = 5; // Size of the diamond (number of rows for the top half)
        printDiamond(n, 0, 0, true);
    }

    public static void printDiamond(int n, int row, int col, boolean top) {
        if (row < n && top) {
            // Print leading spaces for the top half
            printSpaces(n - row - 1);
            // Print stars for the top half
            printStars(2 * row + 1);
            System.out.println();
            printDiamond(n, row + 1, col, top);
        } else if (row > 0) {
            // Print leading spaces for the bottom half
            printSpaces(n - row);
            // Print stars for the bottom half
            printStars(2 * row - 1);
            System.out.println();
            printDiamond(n, row - 1, col, false);
        }
    }

    public static void printSpaces(int count) {
        if (count > 0) {
            System.out.print(" ");
            printSpaces(count - 1);
        }
    }

    public static void printStars(int count) {
        if (count > 0) {
            System.out.print("*");
            printStars(count - 1);
        }
    }
}
