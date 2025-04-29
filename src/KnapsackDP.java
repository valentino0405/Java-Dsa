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


//Outer loop: i = 0 to 5 (items)
//Inner loop: w = 0 to 10 (weights)
//🛒 Quick Example:
//
//Item No.	Weight (kg)	Profit (₹)
//1	3	10
//2	2	5
//3	4	12
//4	5	15
//5	1	8
//Capacity = 10kg.
//
//Suppose you are at Item 3 (Weight 4kg, Profit 12₹), bag capacity 10kg:
//
//Option 1: Not pick Item 3
//➔ You stay at 10kg capacity, and keep whatever earlier profit you had (dp[2][10]).
//
//Option 2: Pick Item 3
//➔ 4kg gets used. ➔ Remaining capacity = 6kg. ➔ Best earlier profit with 6kg (dp[2][6]) is 15₹ (calculated already from earlier). ➔ So, Total = Current Profit (12) + Earlier Best (15) = 27₹.
//
//Now, compare:
//
//Don't pick: maybe profit = 15₹.
//
//Pick: profit = 27₹.
//
//➡️ You obviously pick!
//
//🛍️ Real life visualization:
//Imagine you're stacking gold jewelry in a bag.
//Every time you pick something heavy,
//you check:
//
//"Okay, now my bag has less space.
//
//What smart collection could I have made from earlier lighter items with this remaining space?"
//
//You don't re-think all choices — you just remember the best possible earlier profits (already computed in DP).
//
//That's why Dynamic Programming (DP) is like "future you sending advice to past you". 😂
//
//Summary Sentence:
//When you pick a new jewelry item, the profit is the value of that item now + the best profit you could have made with the leftover bag space if you had made smarter decisions before!
//
//Would you also like a small visual drawing (like showing a bag filling and arrows from dp values)? 🎒✨
//It would make it even more crystal-clear if you want!
//
//
//
//
//
//
//
//You said:
//so yeah its basically every permutation and combinayion ..like okay this is my current weight and profit okay so if i add the weight so my remaining is some capacity .....so i go those many positionss behind by weight and check whether i could have taken some more .........by including or excluding that item ...........
//
//
//ChatGPT said:
//🎯 Exactly! You totally got it! 🔥
//
//Let me put your intuition into simple words, polishing your thought:
//
//At every item,
//you're thinking like:
//
//➔ “If I include this item, my bag's capacity reduces by its weight. Then I check: With that reduced space, what is the best profit I could have built earlier?"
//
//➔ “Or, if I exclude this item, I just stick with the earlier profit (above me in the table) without adding anything new.”
//
//And then you pick the better of the two.
//
//✅ So yes, it's like trying every possible combination of:
//
//"Take it + see if remaining space helps,"
//OR
//
//"Don't take it + stick to previous best."
//
//✅ The idea of "going those many positions behind by weight" is super accurate too!
//Because when you pick an item, you jump back to a lighter capacity (w - weight[i-1]) and ask:
//
//👉 "What was the best solution possible with that much remaining space?"
//
//🧠 In pure simple terms:
//Include current item → profit[i-1] + dp[i-1][remaining capacity]
//
//Exclude current item → dp[i-1][same capacity]
//
//Choose maximum of both.
//
//🛒 Real-world Visualization:
//Imagine you're loading a truck with boxes (items) to maximize their value:
//
//Every time you load a box (add an item), you reduce available truck space.
//
//Then you think:
//“Okay, earlier when my truck was this much empty, what was the best set of boxes I could have picked?”
//
//You always plan smartly, remembering the best previous collections.
//
//That's why DP table saves earlier calculations — so you don't recalculate every combination again.
//
//🧹 Summary One-Liner:
//


//"If I decide to pick this new item right now, how much capacity is left?"
//"In that leftover capacity, what was the best profit I had already achieved in the past?"
/*  } else if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], profit[i - 1] + dp[i - 1][w - weight[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                } so basically this euqation checks if my current can fit into the bag ..or not by increasing in every iteration for clacultion purposes ......and then i check ki if true then i compare first dp[i - 1][w] and  profit[i - 1] + dp[i - 1][w - weight[i - 1]].meaning if i dont add so take the upper one but if add the profit so in past my pairing it with every  item whats max can i get else if the condition is false means if current incoming wieght cant be put into the bag .....as per alculaions so take the previous wieght profit itself from upper cell*/


//It's like playing Include-Exclude game at every step — "Should I include this new shiny thing and reduce my space? Or just stick with what I had earlier?