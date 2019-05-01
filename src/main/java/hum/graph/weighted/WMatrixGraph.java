package hum.graph.weighted;


/**
 * @author hum
 */
public class WMatrixGraph implements WGraph {
    private int N, M;
    private boolean directed;
    private Edge[][] g;


    public WMatrixGraph(int n, boolean directed) {
        this.N = n;
        this.M = 0;
        this.directed = directed;
        g = new Edge[n][n];
    }

    @Override
    public void addEdge(int v, int w, double weight) {
        if (v < 0 || v >= N || w < 0 || w >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        if (hasEdge(v, w)) {
            g[v][w] = null;
            if (!directed) {
                g[w][v] = null;
            }
            M--;
        }
        g[v][w] = new Edge(v, w, weight);
        if (!directed) {
            g[w][v] = new Edge(w, v, weight);
        }
        M++;
    }


    @Override
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= N || w < 0 || w >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        return g[v][w] != null;
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
            for (int j = 0; j < N; j++) {
                if (g[i][j] != null) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public int junctionVertex(int v) {
        if (v < 0 || v >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (g[v][i] != null) {
                count++;
            }
        }
        return count;

    }

    @Override
    public Edge indexEdge(int v, int index) {
        if (v < 0 || v >= N || index < 0 || index >= junctionVertex(v)) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        int count = -1;
        int i;
        for (i = 0; i < N; i++) {
            if (g[v][i] != null) {
                count++;
            }
            if (count == index) {
                break;
            }
        }
        return g[v][i];
    }

    public void showMatrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (g[i][j] != null) {
                    System.out.print(g[i][j].getWeight() + " ");
                } else {
                    System.out.print("NULL ");
                }
            }
            System.out.println();
        }
    }

}
