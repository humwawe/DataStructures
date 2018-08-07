package hum.graph.weightedGraph;

/**
 * @author hum
 */
public interface WGraph {
    public void addEdge(int v, int w, double weight);

    public boolean hasEdge(int v, int w);

    public int getV();

    public int getE();

    public void printGraph();

    public int junctionVertex(int v);

    public Edge indexEdge(int v, int index);


}

