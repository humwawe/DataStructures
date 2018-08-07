package hum.graph.weightedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hum
 */
public class PrimWGraph {
    int N;
    private WGraph graph;
    private boolean[] marked;
    /**
     * 可以用更好的数据结构，比如索引堆，map等
     */
    private List<Edge> edgeTo;
    private List<Edge> mst;
    private double mstWeight;
    private Edge temp;

    public PrimWGraph(WGraph graph) {
        N = graph.getV();
        this.graph = graph;
        edgeTo = new ArrayList<>();
        marked = new boolean[N];
        mst = new ArrayList<>();
        temp = new Edge(-1, -1, Double.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            edgeTo.add(temp);
        }


        prim(0);

        mstWeight = mst.get(0).getWeight();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight += mst.get(i).getWeight();
        }
    }

    public void prim(int s) {
        visit(s);
        while (getMinEdge() != temp) {
            Edge e = getMinEdge();
            mst.add(e);
            visit(e.getD());
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
                if (next.compareTo(edgeTo.get(w)) < 0) {
                    edgeTo.set(w, next);
                }
            }
        }
    }

    private Edge getMinEdge() {
        Edge minEdge = temp;
        for (int i = 0; i < N; i++) {
            if (!marked[i]) {
                if (edgeTo.get(i).compareTo(minEdge) < 0) {
                    minEdge = edgeTo.get(i);
                }
            }
        }
        return minEdge;
    }


    public List<Edge> mstEdge() {
        return mst;
    }

    public double result() {
        return mstWeight;
    }
}
