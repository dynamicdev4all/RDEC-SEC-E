import java.util.Arrays;

public class KaprekarConstant {

    public static int kaprekarProcess(int number) {
        int count = 0;
        while (number != 6174) {
            String numStr = String.format("%04d", number);
            char[] digits = numStr.toCharArray();
            Arrays.sort(digits);
            String ascending = new String(digits);
            String descending = new StringBuilder(ascending).reverse().toString();
            number = Integer.parseInt(descending) - Integer.parseInt(ascending);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int number = 3524; // You can change this number to test with others
        if (number >= 1000 && number <= 9999 && !String.valueOf(number).equals("0000")) {
            int steps = kaprekarProcess(number);
            System.out.println("Reached Kaprekar's constant 6174 in " + steps + " steps.");
        } else {
            System.out.println("Please enter a valid four-digit number with at least two different digits.");
        }
    }
}
