import java.util.ArrayList;

public class Node implements Comparable<Node> {
    protected final String name;
    protected final double latitude;
    protected final double longitude;
    protected final ArrayList<Node> adjacent;
    protected double distance;
    protected Node prev;

    public Node(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.adjacent = new ArrayList<>();
        this.distance = Double.MAX_VALUE;
    }

    public double getDistance(Node node) {
        return Math.sqrt(Math.pow(node.latitude - this.latitude, 2) + Math.pow(node.longitude - this.longitude, 2));
    }

    @Override
    public int compareTo(Node node) {
        return Double.compare(this.distance, node.distance);
    }
}
