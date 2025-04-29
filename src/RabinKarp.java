import java.util.Scanner;

public class RabinKarp {
    public static void searchPattern(String T, String P, int d, int q) {
        int n = T.length();
        int m = P.length();
        int h = 1;
        int p = 0, t = 0, spuriousHits = 0;
        boolean found = false;

        // Calculate h = d^(m-1) % q
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % q;

        // Calculate initial hash values
        for (int i = 0; i < m; i++) {
            p = (d * p + P.charAt(i)) % q;
            t = (d * t + T.charAt(i)) % q;
        }

        System.out.println("\nSearching for pattern \"" + P + "\" in text \"" + T + "\"");

        // Slide the pattern over text one by one
        for (int s = 0; s <= n - m; s++) {
            if (p == t) {
                boolean match = true;
                // Check character by character
                for (int i = 0; i < m; i++) {
                    if (T.charAt(s + i) != P.charAt(i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    System.out.println("Pattern found at shift " + s);
                    found = true;
                } else {
                    spuriousHits++;
                }
            }

            // Calculate hash value for next window of text
            if (s < n - m) {
                t = (d * (t - T.charAt(s) * h) + T.charAt(s + m)) % q;//New Hash = (Radix * (Old Hash - Leftmost Character Contribution) + New Rightmost Character) mod Prime

                if (t < 0)
                    t += q;
            }
        }

        if (!found)
            System.out.println("No match found!");
        System.out.println("\nTotal spurious hits: " + spuriousHits);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your full name (used as Text T): ");
        String T = scanner.nextLine();

        System.out.print("Enter a non-trivial Pattern P (min 4 characters): ");
        String P = scanner.nextLine();

        System.out.print("Enter radix value d (e.g., 26, 52, 256): ");
        int d = scanner.nextInt();

        System.out.print("Enter a prime number q: ");
        int q = scanner.nextInt();

        searchPattern(T, P, d, q);
        scanner.close();
    }
}


/*
 🔥 Quick Example with Your Inputs
Text T = "ValentinoGomes"

Pattern P = "ntino" (m = 5)

Suppose s = 4:

Window covers "ntino" (text from position 4 to 8).

To slide forward:

Remove T.charAt(4) = 'n'

Add T.charAt(4 + 5) = T.charAt(9) = 'G'

Hash is updated by:

Removing 'n'

Adding 'G'
*/