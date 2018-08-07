package hum.graph.unweightedGraph;

/**
 * @author hum
 */
public class MatrixGraph implements Graph {
    private int N, M;
    private boolean directed;
    private boolean[][] g;


    public MatrixGraph(int n, boolean directed) {
        this.N = n;
        this.M = 0;
        this.directed = directed;
        g = new boolean[n][n];
    }

    @Override
    public void addEdge(int v, int w) {
        if (v < 0 || v >= N || w < 0 || w >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        if (hasEdge(v, w)) {
            return;
        }

        g[v][w] = true;
        if (!directed) {
            g[w][v] = true;
        }
        M++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (v < 0 || v >= N || w < 0 || w >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        return g[v][w];
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
                if (g[i][j]) {
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
            if (g[v][i]) {
                count++;
            }
        }
        return count;

    }

    @Override
    public int indexVertex(int v, int index) {
        if (v < 0 || v >= N || index < 0 || index >= junctionVertex(v)) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        int count = -1;
        int i;
        for (i = 0; i < N; i++) {
            if (g[v][i]) {
                count++;
            }
            if (count == index) {
                break;
            }
        }
        return i;
    }

    public void showMatrix() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(g[i][j] + " ");
            }
            System.out.println();
        }
    }

}
