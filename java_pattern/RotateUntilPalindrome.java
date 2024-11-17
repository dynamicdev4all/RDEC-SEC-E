import java.util.Scanner;

public class RotateUntilPalindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scanner.nextInt();
        scanner.close();

        int rotations = 0;
        while (!isPalindrome(num)) {
            num = rotateNumber(num);
            rotations++;
        }
        System.out.println("Palindromic number: " + num);
        System.out.println("Rotations needed: " + rotations);
    }

    public static int rotateNumber(int num) {
        int lastDigit = num % 10;
        int remainingNum = num / 10;
        int numDigits = (int) Math.log10(num) + 1;
        return lastDigit * (int) Math.pow(10, numDigits - 1) + remainingNum;
    }

    public static boolean isPalindrome(int num) {
        int originalNum = num;
        int reversedNum = 0;

        while (num != 0) {
            int digit = num % 10;
            reversedNum = reversedNum * 10 + digit;
            num /= 10;
        }

        return originalNum == reversedNum;
    }
}
