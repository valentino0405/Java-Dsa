import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Code7 {
    static int V;
    static int[] color;
    static List<String> solutions;
    static int[][] graph;
    static String[] colorNames = {"R", "G", "B", "Y", "O", "P", "C", "W", "B"};

    static boolean isSafe(int v, int c) {
        for (int i = 0; i < V; i++) {
            if (graph[v][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    static void graphColoring(int v, int m) {
        if (v == V) {

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < V; i++) {
                sb.append(colorNames[color[i] - 1]).append(" ");
            }
            solutions.add(sb.toString().trim());
            return;
        }
        for (int c = 1; c <= m; c++) {
            if (isSafe(v, c)) {
                color[v] = c;
                graphColoring(v + 1, m);
                color[v] = 0;
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        V = sc.nextInt();
        System.out.print("Enter number of colors: ");
        int m = sc.nextInt();
        graph = new int[V][V];

        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        color = new int[V];
        solutions = new ArrayList<>();
        graphColoring(0, m);

        if (solutions.isEmpty()) {
            System.out.println("No possible coloring exists.");
        } else {
            System.out.println("Possible color patterns:");
            for (String pattern : solutions) {
                System.out.println(pattern);
            }
        }
    }
}
