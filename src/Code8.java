import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Code8 {
    static int[] w; // Array of weights (sorted in increasing order)
    static int m; // Target sum
    static int n; // Number of elements
    static int[] x; // Solution vector to track included elements
    static List<List<Integer>> result = new ArrayList<>(); // To store subsets
    static List<List<Integer>> solutionVectors = new ArrayList<>(); // To store corresponding solution vectors

    // Backtracking function
    static void sumOfSubsets(int s, int idx, int r) {
        // Generate left child (include w[idx])
        x[idx] = 1;
        if (s + w[idx] == m) {
            storeSubset(idx); // Store the subset
            storeSolutionVector(); // Store the solution vector
        } else if (idx + 1 < n && s + w[idx] + w[idx + 1] <= m) {
            sumOfSubsets(s + w[idx], idx + 1, r - w[idx]);
        }

        // Generate right child (exclude w[idx])
        x[idx] = 0;
        if (s + r - w[idx] >= m && idx + 1 < n) {
            sumOfSubsets(s, idx + 1, r - w[idx]);
        }
    }

    // Function to store the subset in the result list
    static void storeSubset(int idx) {
        List<Integer> subset = new ArrayList<>();
        for (int i = 0; i <= idx; i++) {
            if (x[i] == 1) {
                subset.add(w[i]);
            }
        }
        result.add(subset);
    }

    // Function to store the solution vector in the solutionVectors list
    static void storeSolutionVector() {
        List<Integer> solutionVector = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            solutionVector.add(x[i]);
        }
        solutionVectors.add(solutionVector);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no of Weights: ");
        n = sc.nextInt();
        w = new int[n];
        x = new int[n];

        System.out.print("Enter Elements: ");
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }

        System.out.print("Enter the Target Sum: ");
        m = sc.nextInt();

        Arrays.sort(w); // Ensure weights are sorted in increasing order
        int r = Arrays.stream(w).sum(); // Total sum of weights

        sumOfSubsets(0, 0, r);

        if (result.isEmpty()) {
            System.out.println("No subsets found with sum " + m);
        } else {
            System.out.println("Subsets with sum " + m + ": ");
            for (int i = 0; i < result.size(); i++) {
                System.out.println("Subset: " + result.get(i) + " | Solution Vector: " + solutionVectors.get(i));
            }
        }
    }
}
