package hum.graph.weightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author hum
 */
public class LazyPrimWGraph {
    int N;
    private WGraph graph;
    private boolean[] marked;
    private Queue<Edge> queue;
    private List<Edge> mst;
    private double mstWeight;

    public LazyPrimWGraph(WGraph graph) {
        N = graph.getV();
        this.graph = graph;
        queue = new PriorityQueue<>();
        marked = new boolean[N];
        mst = new ArrayList<>();

        prim(0);

        mstWeight = mst.get(0).getWeight();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight += mst.get(i).getWeight();
        }
    }

    public void prim(int s) {
        visit(s);
        while (!queue.isEmpty()) {
            Edge e = queue.poll();
            if (marked[e.getS()] == marked[e.getD()]) {
                continue;
            }
            mst.add(e);
            if (!marked[e.getS()]) {
                visit(e.getS());
            } else {
                visit(e.getD());
            }
        }
    }

    private void visit(int v) {
        if (marked[v]) {
            throw new IllegalArgumentException("vertex already visited.");
        }
        marked[v] = true;

        for (int j = 0; j < graph.junctionVertex(v); j++) {
            Edge next = graph.indexEdge(v, j);
            if (!marked[next.otherVertex(v)]) {
                queue.add(next);
            }
        }
    }


    public List<Edge> mstEdge() {
        return mst;
    }

    public double result() {
        return mstWeight;
    }
}
