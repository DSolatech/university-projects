public class Graph{
    private int n; //Numbers of nodes
    private int y;
    private int[][] costs;

    public Graph(int n, int y){
        this.n = n;
        this.y = y;

        this.costs = new int[n + 1][n + 1]; //Nodes are 1-indexed to avoid problems
    }

    //Method to add a edge
    public void addEdge(int node1, int node2, int weight) {
        if (node1 > node2) {
            int temp = node1;
            node1 = node2;
            node2 = temp;
        }
            this.costs[node1][node2] = weight; //Using only the upper triangular part of the symmetric matrix to save memory
    }

    public int getCost(int node1, int node2){
        if (node1 < node2){
            return this.costs[node1][node2];
        } else if (node1 > node2){
            return this.costs[node2][node1];
        } else {
            return 0;
        }
    }

    public int getN(){
        return this.n;
    }
}