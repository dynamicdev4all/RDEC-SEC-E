


import java.util.Scanner;

public class HappyNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int num = scanner.nextInt();
        scanner.close();

        if (isHappy(num)) {
            System.out.println(num + " is a Happy Number.");
        } else {
            System.out.println(num + " is not a Happy Number.");
        }
    }

    
    public static boolean isHappy(int num) {
        // Store seen numbers to detect cycles
        int[] seen = new int[1000];
        int index = 0;

        while (num != 1 && index < seen.length) {
            if (seen[num] == 1) {
                // Cycle detected, not Happy
                return false;
            }
            seen[num] = 1;
            seen[index++] = num;

            // Calculate sum of squares of digits
            num = sumOfSquares(num);
        }

        // Happy if reached 1
        return num == 1;
    }

   
    public static int sumOfSquares(int num) {
        int sum = 0;
        while (num != 0) {
            int digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }
}


