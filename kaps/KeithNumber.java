import java.util.ArrayList;
import java.util.List;

public class KeithNumber {

    public static boolean isKeith(int number) {
        String numStr = Integer.toString(number);
        List<Integer> digits = new ArrayList<>();
        
        for (char ch : numStr.toCharArray()) {
            digits.add(Character.getNumericValue(ch));
        }

        int sum = 0;
        while (sum < number) {
            sum = 0;
            for (int i = 0; i < digits.size(); i++) {
                sum += digits.get(i);
            }
            if (sum == number) {
                return true;
            }
            digits.add(sum);
            digits.remove(0);
        }
        return false;
    }

    public static void main(String[] args) {
        int number = 14; // You can change this number to test with others
        if (isKeith(number)) {
            System.out.println(number + " is a Keith number.");
        } else {
            System.out.println(number + " is not a Keith number.");
        }
    }
}
