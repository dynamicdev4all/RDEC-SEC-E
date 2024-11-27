import java.util.Scanner;

public class AutomorphicNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        scanner.close();

        if (isAutomorphic(num)) {
            System.out.println(num + " is an Automorphic number.");
        } else {
            System.out.println(num + " is not an Automorphic number.");
        }
    }

    public static boolean isAutomorphic(int num) {
        int square = num * num;
        int tempNum = num;
        
        while (tempNum > 0) {
            if (tempNum % 10 != square % 10) {
                return false;
            }
            tempNum /= 10;
            square /= 10;
        }
        
        return true;
    }
}

