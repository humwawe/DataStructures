package hum.graph.unweighted;

/**
 * @author hum
 */
public interface Graph {
    public void addEdge(int v, int w);

    public boolean hasEdge(int v, int w);

    public int getV();

    public int getE();

    public void printGraph();

    public int junctionVertex(int v);

    public int indexVertex(int v, int index);


}

