import java.util.*;

public class Newpk {
    static class DisjointSet{
        int[] parent;
        int[] rank;

        public DisjointSet(int n)
        {
            parent=new int[n];
            rank=new int [n];
            for(int i=0;i<n;i++)
            {
                parent[i]=i;
                rank[i]=0;
            }
        }

        public int find(int x){
            if(parent[x]!=x){
                parent[x]=find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x,int y)
        {
          int rootX=find(x);
          int rootY=find(y);
          if(rootX!=rootY)
          {
              if(rank[rootX]>rank[rootY]) {
                parent[rootY]=rootX;
              }else if(rank[rootX]<rank[rootY]){
                  parent[rootX]=rootY;
              }else{
                  parent[rootY]=rootX;
                  rank[rootX]++;
              }
          }
        }


    }
    public static void printTables(int[] distances,int[] parent,int v)
    {
        System.out.print("\nIndex\t\t");
        for(int i=0;i<v;i++){
            System.out.print(i+ "\t");
        }
        System.out.print("\nDistances\t");
        for(int i=0;i<v;i++){
            System.out.print(distances[i]+ "\t");
        }
        System.out.print("\nParents\t\t");
        for(int i=0;i<v;i++)
        {
            System.out.print(parent[i]+ "\t");
        }
        System.out.println();
    }


    public static void Kruskals(PriorityQueue<int[]> pq,int v)
    {
        int edgesCount=0;
        DisjointSet ds=new DisjointSet(v);
        int weightCount=0;

        System.out.println("Edges in MST: ");
        while(!pq.isEmpty() && edgesCount<v-1)
        {
            int[] curr=pq.poll();
            int u=curr[0];
            int v1=curr[1];
            int weight=curr[2];
            if(ds.find(u) !=ds.find(v1)){
                ds.union(u,v1);
                edgesCount++;
                System.out.println(u+ "->"+ v1 +"with weight"+ weight);
                weightCount +=weight;
            }
        }
        System.out.println("Total MST Weight: " + weightCount);
    }
    public static void Prims(ArrayList<ArrayList<int[]>> adj,int v,int src)
    {
        int[] distances=new int[v];
        int[] parent=new int[v];
        boolean[] inMST=new boolean[v];
        Arrays.fill(distances,100);
        Arrays.fill(parent,-1);

        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)-> a[1]- b[1]);
        distances[src]=0;
        pq.add(new int[]{src,0});
        int totalWeight=0;
        int step=1;

        while(!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];

            if (inMST[curNode]) continue;

            inMST[curNode] = true;

            if (parent[curNode] != -1) {
                totalWeight += distances[curNode];
            }
            for (int[] neighbor : adj.get(curNode)) {
                int nextNode = neighbor[0];
                int weight = neighbor[1];

                if (!inMST[nextNode] && weight < distances[nextNode]) {
                    distances[nextNode] = weight;
                    parent[nextNode] = curNode;
                    pq.add(new int[]{nextNode, weight});
                }
            }
            System.out.print("\nStep-"+step);
            printTables(distances,parent,v);
            step++;
        }
        System.out.println("\nMST Total Weight: "+totalWeight);
    }


    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int v=sc.nextInt();
        System.out.print("Enter number of edges: ");
        int e=sc.nextInt();

        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[2]-b[2]);
        ArrayList<ArrayList<int[]>> adj =new ArrayList<>();
        for(int i=0;i<v;i++) adj.add(new ArrayList<>());

        System.out.println("Enter edges in format: source destination weight");
        for(int i=0;i<e;i++)
        {
            int u=sc.nextInt();
            int v1=sc.nextInt();
            int weight=sc.nextInt();
            pq.add(new int[]{u,v1,weight});
            adj.get(u).add(new int[]{v1,weight});
            adj.get(v1).add(new int[]{u,weight});

            System.out.print("Enter the Source Vertex: ");
            int src=sc.nextInt();

            System.out.println("\nKruskal's Algorithm: ");
            Kruskals(pq,v);

            System.out.println("\nPrims Algorithm: ");
            Prims(adj,v,src);
        }
    }

}
