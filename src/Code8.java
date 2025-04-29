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
        } else if (idx + 1 < n && s + w[idx] + w[idx + 1] <= m) {                       //jitna abhi tak add kiya kiya hai subset mein and jo current add karnege plus jo remaining aana hai ye sab bhai add karke overflow na ho jaye
            sumOfSubsets(s + w[idx], idx + 1, r - w[idx]);
        }

        // Generate right child (exclude w[idx])
        x[idx] = 0;                                  //idhar nikal rahe so basically hum dekh rahe hai ki bande ko nikal kar bhi jo apun logo ne abhi tak add kiya hua hai and remaining aaane wala hai - current bande ko nikal ke bhi apna target pohouch sakte hai ki nahi
        if (s + r - w[idx] >= m && idx + 1 < n) {       //so  sumOfSubsets(s, mein add karne ka jarurat hi nahi in function call like s + w[idx]...no need to do that.....as we aint adding so no need to call and add
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


/*
Left Child (Include current element):
✅ Decision: Include w[idx] in subset ➔ set x[idx] = 1

✅ Condition: If (s + w[idx] == m) → 🎯 Solution found! (store subset & solutionVector)

🚀 Else:
if including current w[idx] and next w[idx+1] does not overshoot m ➔
➔ Go deeper (recursive call) to next level ➔ sumOfSubsets(s + w[idx], idx + 1, r - w[idx])

Right Child (Exclude current element):
❌ Decision: Exclude w[idx] ➔ set x[idx] = 0

🚀 Condition:
if remaining weight is still enough to possibly reach m ➔
➔ Go deeper (recursive call) to next level ➔ sumOfSubsets(s, idx + 1, r - w[idx])

🧩 General Equation (in words):
🔵 Left Child:
➔ Include w[idx] ➔ Add it to current sum ➔ Go to idx+1 with updated sum and reduced remaining weight.
➔ If sum == target → store solution. Else continue.

🔴 Right Child:
➔ Exclude w[idx] ➔ Keep current sum same ➔ Go to idx+1 with reduced remaining weight.
➔ If total possible remaining is still enough to reach target → continue.

*/