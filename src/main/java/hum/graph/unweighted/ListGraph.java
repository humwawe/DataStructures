package hum.graph.unweighted;


import java.util.ArrayList;

/**
 * @author hum
 */
public class ListGraph implements Graph {
    private int N, M;
    private boolean directed;
    private ArrayList<Integer>[] g;


    public ListGraph(int n, boolean directed) {
        this.N = n;
        this.M = 0;
        this.directed = directed;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

    }

    @Override
    public void addEdge(int v, int w) {
        if (v < 0 || v >= N || w < 0 || w >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }

        g[v].add(w);
        if (v != w && !directed) {
            g[w].add(v);
        }
        M++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= N || w < 0 || w >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        for (int i = 0; i < g[v].size(); i++) {
            if (g[v].get(i) == w) {
                return true;
            }
        }
        return false;
    }


    @Override
    public int getV() {
        return N;
    }

    @Override
    public int getE() {
        return M;
    }

    @Override
    public void printGraph() {
        for (int i = 0; i < N; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < g[i].size(); j++) {
                System.out.print(g[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    @Override
    public int junctionVertex(int v) {
        if (v < 0 || v >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        return g[v].size();
    }

    @Override
    public int indexVertex(int v, int index) {
        if (v < 0 || v >= N || index < 0 || index >= junctionVertex(v)) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        return g[v].get(index);
    }


}
