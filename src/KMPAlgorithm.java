import java.util.Scanner;

public class KMPAlgorithm {

    public static void computePrefixFunction(String P, int[] pi) {
        pi[0] = 0;
        int i = 0;
        for (int j = 1; j < P.length(); j++) {
            while (i > 0 && P.charAt(i) != P.charAt(j)) {
                i = pi[i - 1];
            }
            if (P.charAt(i) == P.charAt(j)) {
                i++;
            }
            pi[j] = i;
        }
    }

    public static void KMPMatch(String T, String P) {
        int n = T.length();
        int m = P.length();
        int[] pi = new int[m];
        computePrefixFunction(P, pi);

        System.out.println("\n--- Prefix Table (π) ---");
        for (int value : pi) {
            System.out.print(value + " ");
        }
        System.out.println();

        int i = 0;
        System.out.println("\n--- KMP Match Results ---");
        for (int j = 0; j < n; j++) {
            while (i > 0 && P.charAt(i) != T.charAt(j)) {
                i = pi[i - 1];
            }
            if (P.charAt(i) == T.charAt(j)) {
                i++;
            }
            if (i == m) {
                System.out.println("Pattern found at index " + (j - m + 1) +
                        " (Position " + (j - m + 2) + " in text)");
                i = pi[i - 1];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string (no spaces): ");
        String T = scanner.nextLine();

        System.out.print("Enter a pattern to search (at least 6 characters, starting after the 3rd character of your name): ");
        String P = scanner.nextLine();

        if (P.length() < 6) {
            System.out.println("Error: Pattern must be at least 6 characters long.");
            System.exit(1);
        }

        if (T.length() < 3 || !T.substring(3).contains(P)) {
            System.out.println("Error: Pattern must occur at least once in your name after the third character.");
            System.exit(1);
        }

        System.out.println("\n--- Input Summary ---");
        System.out.println("Text    : " + T);
        System.out.println("Pattern : " + P);

        KMPMatch(T, P);
    }
}
