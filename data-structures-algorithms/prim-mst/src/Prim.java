import java.util.ArrayList;
import java.util.List;

public class Prim {
    private Graph graph;
    private int n;
    private boolean trace;
    private List<Edge> mst;
    private int[] minNode;
    private int[] minCost;

    public Prim(Graph graph, boolean trace){

        this.graph = graph;
        this.trace = trace;
        this.n = graph.getN();
        this.mst = new ArrayList<>();
        this.minCost = new int[n+1];
        this.minNode = new int[n+1];

    }

    public List<Edge> getMST(){

        this.minCost[1] = -1;
        
        if (this.trace){
            System.out.println(">> Starting Prim algorithm. Selecting node 1 like first node");
        }

        for(int i = 2; i <= this.n; i++){
            minNode[i] = 1;
            minCost[i] = graph.getCost(1, i);
        }

        for(int i = 1; i <=this.n - 1; i++){

            int min = Integer.MAX_VALUE;
            int node = -1;

            for(int j = 2; j <= this.n; j++){
                if(0 <= minCost[j] && min > minCost[j]){
                    min = minCost[j];
                    node = j;
                }
            }

            if (node == -1) {
                break; // no more reachable nodes
            }

            if (this.trace) {
                System.out.println(">> Selecting node " + node + " connected to " + minNode[node] + " Cost: " + min + ")");
            }

            Edge a = new Edge(minNode[node], node, min);
            mst.add(a);
            minCost[node] = -1;

            for (int j = 2; j <= this.n; j++) {
                int cost = graph.getCost(j, node);
                if (minCost[j] != -1 && cost >= 0 && cost < minCost[j]) {
                    minCost[j] = cost;
                    minNode[j] = node;
                }
            }
        }

        return this.mst;
    }

}
