import java.util.*;

public class KnapsackDP {
    public static void print(int[][] dp, int n, int W, int[] profits, int[] weights) {
        System.out.print("P/W\t");
        for (int w = 0; w <= W; w++) {
            System.out.print(w + "\t");
        }
        System.out.println();

        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                System.out.print("0,0 \t");
            } else {
                System.out.print(profits[i - 1] + "," + weights[i - 1] + "\t");
            }
            for (int j = 0; j <= W; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void knapSack(int[] profit, int[] weight, int W, int n, int[][] dp) {
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], profit[i - 1] + dp[i - 1][w - weight[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Profit: " + dp[n][W]);
        System.out.println("DP Table:");
        print(dp, n, W, profit, weight);

        System.out.print("Selected items (Profit, Weight): ");
        int w = W;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.print("(" + profit[i - 1] + ", " + weight[i - 1] + ") ");
                w -= weight[i - 1];
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of Items: ");
        int n = sc.nextInt();

        int[] profits = new int[n];
        int[] weights = new int[n];

        System.out.print("Enter the maximum weight: ");
        int W = sc.nextInt();

        System.out.print("Enter Profits in Sequence: ");
        for (int i = 0; i < n; i++) {
            profits[i] = sc.nextInt();
        }

        System.out.print("Enter Weights in Sequence: ");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        int[][] dp = new int[n + 1][W + 1];
        knapSack(profits, weights, W, n, dp);
    }
}
