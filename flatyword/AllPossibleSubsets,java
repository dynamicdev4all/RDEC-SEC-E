import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class AllPossibleSubsets {
    public static void main(String[] args) {
        // Create a scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for input
        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();
        
        // Generate all possible subsets (power set)
        Set<String> subsets = generateAllSubsets(inputString);
        
        // Print the subsets
        System.out.println("All possible subsets:");
        for (String subset : subsets) {
            System.out.println(subset);
        }
        
        // Close the scanner
        scanner.close();
    }

    // Method to generate all possible subsets of the input string
    public static Set<String> generateAllSubsets(String inputString) {
        Set<String> resultSet = new HashSet<>();
        int n = inputString.length();
        
        // There are 2^n subsets for a set with n elements
        // Loop through all possible combinations using bitmasking
        for (int mask = 0; mask < (1 << n); mask++) {
            StringBuilder subset = new StringBuilder();
            
            // For each bit in mask, include the corresponding character from the input string
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.append(inputString.charAt(i));
                }
            }
            
            // Add the subset to the result set
            resultSet.add(subset.toString());
        }
        
        return resultSet;
    }
}