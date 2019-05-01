package hum.graph.unweighted;

/**
 * @author hum
 */
public class ComponentGraph {
    int N;
    private Graph graph;
    private boolean[] visited;
    /**
     * 记录是否属于同一个连通图
     */
    private int[] id;
    /**
     * 记录连通图个数
     */
    private int count;

    public ComponentGraph(Graph graph) {
        this.graph = graph;
        N = graph.getV();
        visited = new boolean[N];
        id = new int[N];
        count = 0;
    }

    public int connectedComponents() {
        resetVisited();
        count = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                count++;
                dfs(i);
            }
        }
        return count;
    }

    private void dfs(int v) {
        visited[v] = true;
        id[v] = count;
        for (int j = 0; j < graph.junctionVertex(v); j++) {
            int next = graph.indexVertex(v, j);
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    private void resetVisited() {
        for (int i = 0; i < N; i++) {
            visited[i] = false;
        }
    }

    public boolean isConnected(int v, int w) {
        if (v < 0 || v >= N || w < 0 || w >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        // 还未计算，先计算连通性
        if (id[v] == 0) {
            connectedComponents();
        }
        return id[v] == id[w];
    }
}
