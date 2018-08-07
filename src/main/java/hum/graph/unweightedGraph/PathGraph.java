package hum.graph.unweightedGraph;


import java.util.LinkedList;

/**
 * @author hum
 */
public class PathGraph {
    int N;
    int s;
    private Graph graph;
    private boolean[] visited;
    private int[] from;

    public PathGraph(Graph graph, int s) {
        this.graph = graph;
        this.s = s;
        N = graph.getV();
        if (s < 0 || s >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        visited = new boolean[N];
        from = new int[N];
        for (int i = 0; i < N; i++) {
            from[i] = -1;
        }
        dfs(s);
    }

    public boolean hasPath(int w) {
        if (s < 0 || s >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        return visited[w];
    }

    public LinkedList<Integer> path(int w) {
        if (s < 0 || s >= N) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        LinkedList<Integer> res = new LinkedList<>();
        int p = w;
        while (p != -1) {
            res.addFirst(p);
            p = from[p];
        }
        return res;
    }

    public void showPath(int w) {
        LinkedList<Integer> res = path(w);
        for (int i = 0; i < res.size() - 1; i++) {
            System.out.print(res.get(i) + "->");
        }
        System.out.println(res.getLast());
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int j = 0; j < graph.junctionVertex(v); j++) {
            int next = graph.indexVertex(v, j);
            if (!visited[next]) {
                from[next] = v;
                dfs(next);
            }
        }
    }
}
