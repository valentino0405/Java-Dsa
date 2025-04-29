

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Exp4 {
    static class DisjointSet{
        int[] parent;
        int[] rank;

        public DisjointSet(int n){
            parent = new int[n];
            rank = new int[n];

            for(int i=0 ; i<n ; i++){
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x , int y){
            int rootX = find(x);
            int rootY = find(y);

            if(rootX != rootY){
                if(rank[rootX] > rank[rootY]){
                    parent[rootY] = rootX;
                }else if(rank[rootX] < rank[rootY]){
                    parent[rootX] = rootY;
                }else{
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }


    public static void kruskals(PriorityQueue<int[]> pq , int V){
        int edgeCount = 0;
        ArrayList<int[]> mst = new ArrayList<>();
        DisjointSet ds = new DisjointSet(V);
        int weightCount = 0;

        while(!pq.isEmpty() && edgeCount < V-1){
            int[] current = pq.poll();
            int u = current[0];
            int v = current[1];
            int wt = current[2];


            if(ds.find(u) != ds.find(v)){
                ds.union(u, v);
                mst.add(current);
                edgeCount++;

                weightCount += wt;
            }
        }
    }

    public static void Prims(int[][] adj , int V , int src){
        int[] distances = new int[V];
        int[] parent = new int[V];

        Arrays.fill(distances , 100);
        Arrays.fill(parent , -1);
        parent[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        pq.add(new int[] { src , 0} );
        int totalWt = 0;

        while(!pq.isEmpty()){
            int[] curr =  pq.poll();
            int currNode = curr[0];
            int currDist = curr[1];

            if(currDist > distances[currNode]){
                continue;
            }

            totalWt += currDist;
            int[] list = adj[currNode];
            for(int i=0 ; i<list.length ; i++){
                int wt = list[i];
                if(wt != -1){
                    int newDist = wt;
                    if(newDist < distances[i]){
                        distances[i] = newDist;
                        parent[i] = currNode;
                        pq.add(new int[]{ i  , newDist});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = 5;

        int[][] adj = {
                // 0   1   2   3   4
                { -1, 10,  3, -1, -1 }, // 0
                { -1, -1,  1, -1, -1 }, // 1
                { -1,  4, -1,  8,  2 }, // 2
                { -1, -1, -1, -1,  7 }, // 3
                { -1, -1, -1,  9, -1 }  // 4
        };

//        For Kruskal's
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2]-b[2]);
// Sort By the weight values
        for(int i=0 ; i<V ; i++){
            for(int j=0 ; j<V ; j++){
                if(adj[i][j] != -1 && i < j){
// Since Graph is undirected i < j reduces the entry of same edges twice
                    pq.add(new int[]{ i , j , adj[i][j] } );        // u , v , weight
                }
            }
        }

//        For Prims
        System.out.println("Enter the Source Vertex : ");
        int src = sc.nextInt();



        kruskals(pq , V);
        Prims(adj , V , src);
    }
}
