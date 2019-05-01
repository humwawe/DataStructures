package hum.graph.weighted;

/**
 * @author hum
 */
public class Edge implements Comparable<Edge> {
    private int s, d;
    private double weight;

    public Edge(int s, int d, double weight) {
        this.s = s;
        this.d = d;
        this.weight = weight;
    }

    public int getS() {
        return s;
    }

    public int getD() {
        return d;
    }

    public double getWeight() {
        return weight;
    }

    public int otherVertex(int x) {
        if (x != s && x != d) {
            throw new IllegalArgumentException("vertex doesn't exist.");
        }
        return x == s ? d : s;
    }

    @Override
    public String toString() {
        return s + "-" + d + " : " + weight;
    }


    @Override
    public int compareTo(Edge o) {
        return Double.compare(weight, o.weight);
    }
}
