import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Code8 {
    static int[] w;
    static int m;
    static int n;
    static int[] x;
    static List<List<Integer>> result = new ArrayList<>();
    static List<List<Integer>> solutionVectors = new ArrayList<>();


    static void sumOfSubsets(int s, int idx, int r) {

        x[idx] = 1;
        if (s + w[idx] == m) {
            storeSubset(idx);
            storeSolutionVector();
        } else if (idx + 1 < n && s + w[idx] + w[idx + 1] <= m) {
            sumOfSubsets(s + w[idx], idx + 1, r - w[idx]);
        }


        x[idx] = 0;
        if (s + r - w[idx] >= m && idx + 1 < n) {
            sumOfSubsets(s, idx + 1, r - w[idx]);
        }
    }


    static void storeSubset(int idx) {
        List<Integer> subset = new ArrayList<>();
        for (int i = 0; i <= idx; i++) {
            if (x[i] == 1) {
                subset.add(w[i]);
            }
        }
        result.add(subset);
    }


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

        Arrays.sort(w);
        int r = Arrays.stream(w).sum();

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
