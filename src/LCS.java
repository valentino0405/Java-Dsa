import java.util.Scanner;

public class LCS {
    static char[][] b;

    public static int[][] LCS_Length(String x, String y, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        b = new char[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    b[i][j] = ' '; // No direction for the first row/column
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) { // Match case
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    b[i][j] = '↖'; // Diagonal move
                } else if (dp[i - 1][j] > dp[i][j - 1]) { // Up move
                    dp[i][j] = dp[i - 1][j];
                    b[i][j] = '↑';
                } else { // Left move
                    dp[i][j] = dp[i][j - 1];
                    b[i][j] = '←';
                }
            }
        }
        return dp;
    }

    static void printTable(int[][] dp, String x, String y) {
        System.out.print("\t0\t");
        for (int j = 0; j < y.length(); j++) {
            System.out.format("%c\t", y.charAt(j));
        }
        System.out.println();

        for (int i = 0; i <= x.length(); i++) {
            if (i == 0) {
                System.out.print("0\t");
            } else {
                System.out.format("%c\t", x.charAt(i - 1));
            }
            for (int j = 0; j <= y.length(); j++) {
                System.out.format("%d%c\t", dp[i][j], b[i][j]);
            }
            System.out.println();
        }
    }

    public static void LCS_Print(String x, int i, int j) {
        if (i == 0 || j == 0) {
            return;
        }
        if (b[i][j] == '↖') { // Diagonal (Match case)
            LCS_Print(x, i - 1, j - 1);
            System.out.print(x.charAt(i - 1));
        } else if (b[i][j] == '↑') { // Up
            LCS_Print(x, i - 1, j);
        } else { // Left
            LCS_Print(x, i, j - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string 1 : ");
        String s1 = sc.nextLine();
        System.out.print("Enter the string 2 : ");
        String s2 = sc.nextLine();
        int n = s1.length();
        int m = s2.length();
        int[][] dp = LCS_Length(s1, s2, n, m);
        printTable(dp, s1, s2);
        System.out.print("Longest Common Subsequence: ");
        LCS_Print(s1, n, m);
    }
}
