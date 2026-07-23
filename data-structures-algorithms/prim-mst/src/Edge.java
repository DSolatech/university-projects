public class Edge {
    private int node1;
    private int node2;
    private int cost;

    public Edge(int node1, int node2, int cost){
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
    }

    public int getNode1(){
        return this.node1;
    }

    public int getNode2(){
        return this.node2;
    }

    public int getCost(){
        return this.cost;    
    }
}