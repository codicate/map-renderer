import java.util.HashMap;
import java.util.HashSet;

public class Graph {
    protected HashMap<String, Node> nodes;
    protected HashSet<Edge> edges;
    protected double maxLat;
    protected double maxLong;
    protected double minLat;
    protected double minLong;

    public Graph(HashMap<String, Node> nodes, HashSet<Edge> edges, double maxLat, double maxLong, double minLat, double minLong) {
        this.nodes = nodes;
        this.edges = edges;
        this.maxLat = maxLat;
        this.maxLong = maxLong;
        this.minLat = minLat;
        this.minLong = minLong;
    }
}
