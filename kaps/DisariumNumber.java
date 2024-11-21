public static boolean isDisarium(int number) {
        int sum = 0;
        String numStr = Integer.toString(number);
        for (int i = 0; i < numStr.length(); i++) {
            sum += Math.pow(Character.getNumericValue(numStr.charAt(i)), i + 1);
        }
        return sum == number;
    }

    public static void main(String[] args) {
        int number = 89; // You can change this number to test with others
        if (isDisarium(number)) {
            System.out.println(number + " is a Disarium number.");
        } else {
            System.out.println(number + " is not a Disarium number.");
        }
    }


