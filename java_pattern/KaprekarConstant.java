import java.util.Scanner;

public class KaprekarConstant {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a 4-digit number: ");
        int num = scanner.nextInt();
        scanner.close();

        System.out.println("Kaprekar's Constant: " + findKaprekarConstant(num));
    }

    public static int findKaprekarConstant(int num) {
        int count = 0;
        while (num != 6174) {
            num = kaprekarOperation(num);
            count++;
        }
        System.out.println("Iterations: " + count);
        return num;
    }

    public static int kaprekarOperation(int num) {
        int ascending = sortDigitsAscending(num);
        int descending = sortDigitsDescending(num);

        return descending - ascending;
    }

    public static int sortDigitsAscending(int num) {
        int sorted = 0;
        for (int i = 0; i <= 9; i++) {
            int temp = num;
            while (temp > 0) {
                int digit = temp % 10;
                if (digit == i) {
                    sorted = sorted * 10 + digit;
                }
                temp /= 10;
            }
        }
        return sorted;
    }

    public static int sortDigitsDescending(int num) {
        int sorted = 0;
        for (int i = 9; i >= 0; i--) {
            int temp = num;
            while (temp > 0) {
                int digit = temp % 10;
                if (digit == i) {
                    sorted = sorted * 10 + digit;
                }
                temp /= 10;
            }
        }
        return sorted;
    }
}
