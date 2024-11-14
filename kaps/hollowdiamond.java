public class hollowdiamond {
    static void printStars(int stars) {
            if (stars == 0) {
                return;
            }
            System.out.print("* ");
            printStars(stars - 1);
        }
        static void printSpaces(int spaces) {
            if (spaces == 0) {
                return;
            }
            System.out.print("  ");
            printSpaces(spaces - 1);
        }
        static void printLines(int rows, int spaces) {
            if (rows == 0) {
                return;
            }
            printStars(rows);
            printSpaces(spaces);
            printSpaces(spaces);
            printStars(rows);
            System.out.println();
            printLines(rows - 1, spaces + 1);
        }
        static void printLowerLines(int rows, int spaces) {
            if (rows == 0) {
                return;
            }
            printLowerLines(rows - 1, spaces + 1);
            printStars(rows);
            printSpaces(spaces);
            printSpaces(spaces);
            printStars(rows);
            System.out.println();
        }
        public static void main(String[] args) {
            printLines(5, 0);
            printLowerLines(5, 0);
        }
    }