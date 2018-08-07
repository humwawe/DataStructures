package hum.graph.weightedGraph;

/**
 * @author hum
 */
public class DijkstraWGraph {
    private int N;
    private int start;
    private WGraph graph;
    private boolean[] marked;
    private int[] from;
    private double[] distTo;

    public DijkstraWGraph(WGraph graph) {
        N = graph.getV();
        this.graph = graph;
        from = new int[N];
        distTo = new double[N];
        marked = new boolean[N];


        for (int i = 0; i < N; i++) {
            distTo[i] = Double.MAX_VALUE;
        }

        start = 0;
        distTo[start] = 0;
        dijkstra(start);

    }

    public void dijkstra(int s) {
        visit(s);
        while (getMinPathIndex() != -1) {
            int v = getMinPathIndex();
            visit(v);
        }
    }

    private void visit(int v) {
        if (marked[v]) {
            throw new IllegalArgumentException("vertex already visited.");
        }
        marked[v] = true;

        for (int j = 0; j < graph.junctionVertex(v); j++) {
            Edge next = graph.indexEdge(v, j);
            int w = next.otherVertex(v);
            if (!marked[w]) {
                if (distTo[v] + next.getWeight() < distTo[w]) {
                    distTo[w] = distTo[v] + next.getWeight();
                    from[w] = v;
                }
            }
        }
    }

    private int getMinPathIndex() {
        int index = -1;
        double minPath = Double.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (!marked[i]) {
                if (distTo[i] < minPath) {
                    minPath = distTo[i];
                    index = i;
                }
            }
        }
        return index;
    }


    public double shortestPathTo(int w) {
        return distTo[w];
    }

    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public void showPathTo(int w) {
        if (w != start) {
            showPathTo(from[w]);
        }
        System.out.print(w + "->");
    }
}
