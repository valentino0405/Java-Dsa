import java.util.*;

public class NewDjik {


    public void printTables(int[] distances,int[] parent,int v)
    {
        System.out.println("\n Index \t\t");
        for(int i=0;i<v;i++)
        {
            System.out.print(i + "\t");

        }
        System.out.print("\n Distances\t");
        for(int i=0;i<v;i++)
        {
            System.out.print(distances[i] +"\t");
        }
        System.out.print("\nParents \t");
        for(int i=0;i<v;i++)
        {
            System.out.print(parent[i] +"\t");
        }
        System.out.println();
    }


    public void shortestPath(ArrayList<ArrayList<int[]>> adj ,int v,int src)
    {
        int[] distances=new int[v];
        int[] parent=new int[v];
        Arrays.fill(distances, 234);
        Arrays.fill(parent,-1);

        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[1]-b[1]);
        distances[src]=0;
        pq.add(new int[]{src,0});


        int step=1;
        while(!pq.isEmpty())
        {
            int[] current=pq.poll();
            int curNode=current[0];
            int curDist=current[1];

            if(curDist> distances[curNode])
            {
                continue;
            }

            ArrayList<int[]> list=adj.get(curNode);
            for(int[] edge :list)
            {
                int destNode=edge[0];
                int weight=edge[1];
                int newDist=curDist+weight;
                if(newDist < distances[destNode]){
                    distances[destNode]=newDist;
                    parent[destNode]=curNode;
                    pq.add(new int[] {destNode,newDist});

                }
            }
            System.out.print("Step- "+step);
            printTables(distances,parent,v);
            step++;
        }
        System.out.print("\nAnswer: ");
        printTables(distances,parent,v);
        printPaths(parent,src);
    }

    public void printPaths(int[] parent,int src)
    {
        System.out.println("\nShortest Paths from source "+ src + ":");
        for(int i=0;i<parent.length;i++)
        {
            if(i!=src)
            {
                System.out.print("Path to "+ i +":");
                printPath(i,parent);
                System.out.println();
            }
        }

    }
    public void printPath(int node,int[] parent){
        if(node==-1) return;
        printPath(parent[node],parent);
        System.out.print(node + " ");
    }



    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int v=sc.nextInt();

        ArrayList<ArrayList<int[]>> adj= new ArrayList<>();
        for(int i=0;i<v;i++)
        {
            adj.add(new ArrayList<>());
        }

        System.out.println("Enter number of edges: ");
        int e=sc.nextInt();

        System.out.println("Enter edges(source destination weight): ");
        for(int i=0;i<e;i++)
        {
            int src=sc.nextInt();
            int dest=sc.nextInt();
            int weight=sc.nextInt();
            adj.get(src).add(new int[]{dest,weight});
        }

        System.out.print("Enter the source Vertex: ");
        int src=sc.nextInt();

        NewDjik obj=new NewDjik();
        obj.shortestPath(adj,v,src);
    }
}
