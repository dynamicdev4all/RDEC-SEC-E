public class AutomorphicNumber {

    public static boolean isAutomorphic(int n) {
        int square = n * n;
        String strN = Integer.toString(n);
        String strSquare = Integer.toString(square);
        return strSquare.endsWith(strN);
    }

    public static void main(String[] args) {
        int number = 25; // You can change this number to test with others
        if (isAutomorphic(number)) {
            System.out.println(number + " is an automorphic number.");
        } else {
            System.out.println(number + " is not an automorphic number.");
        }
    }
}