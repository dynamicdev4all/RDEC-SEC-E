public class RotationPalindrome {

    public static boolean isPalindrome(int num) {
        String str = Integer.toString(num);
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static boolean isRotationPalindrome(int num) {
        String str = Integer.toString(num);
        int len = str.length();
        for (int i = 0; i < len; i++) {
            String rotated = str.substring(i) + str.substring(0, i);
            if (isPalindrome(Integer.parseInt(rotated))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int number = 121; // Change this number to test with others
        if (isRotationPalindrome(number)) {
            System.out.println(number + " is a rotation palindrome.");
        } else {
            System.out.println(number + " is not a rotation palindrome.");
        }
    }
}