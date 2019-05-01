package hum.graph.weighted;

import hum.unionfind.UF;
import hum.unionfind.UnionFind6;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author hum
 */
public class KruskalWGraph {
    int N;
    private WGraph graph;
    private Queue<Edge> queue;
    /**
     * 用来判断环
     */
    private UF uf;
    private List<Edge> mst;
    private double mstWeight;

    public KruskalWGraph(WGraph graph) {
        N = graph.getV();
        this.graph = graph;
        queue = new PriorityQueue<>();
        uf = new UnionFind6(N);
        mst = new ArrayList<>();

        kruskal();

        mstWeight = mst.get(0).getWeight();
        for (int i = 1; i < mst.size(); i++) {
            mstWeight += mst.get(i).getWeight();
        }
    }

    public void kruskal() {
        for (int v = 0; v < N; v++) {
            for (int j = 0; j < graph.junctionVertex(v); j++) {
                Edge next = graph.indexEdge(v, j);
                if (next.getS() < next.getD()) {
                    queue.add(next);
                }
            }
        }

        while (!queue.isEmpty() && mst.size() < N - 1) {
            Edge e = queue.poll();
            if (uf.isConnected(e.getD(), e.getS())) {
                continue;
            }
            mst.add(e);
            uf.unionElements(e.getD(), e.getS());
        }

    }

    public List<Edge> mstEdge() {
        return mst;
    }

    public double result() {
        return mstWeight;
    }
}
