import java.util.Scanner;

public class DisariumNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int num = scanner.nextInt();
        scanner.close();

        if (isDisarium(num)) {
            System.out.println(num + " is a Disarium number.");
        } else {
            System.out.println(num + " is not a Disarium number.");
        }
    }

    
    public static boolean isDisarium(int num) {
        int originalNum = num;
        int length = countDigits(num);
        int sum = 0;

        while (num != 0) {
            int digit = num % 10;
            sum += (int) Math.pow(digit, length--);
            num /= 10;
        }

        return sum == originalNum;
    }

   
    public static int countDigits(int num) {
        if (num == 0) return 1;
        int count = 0;
        while (num != 0) {
            num /= 10;
            count++;
        }
        return count;
    }
}



