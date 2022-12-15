import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Panel extends JPanel {
    private final Graph graph;
    private String start;
    private String end;
    private HashSet<String> path;

    public Panel(Graph graph) {
        this.graph = graph;
    }

    public Panel(Graph graph, String start, String end) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        this.path = new HashSet<>();
    }

    public void paintComponent(Graphics g) {
        if (start != null && end != null) getShortestPath();

        super.paintComponent(g);
        g.setColor(Color.BLACK);

        double rangex = graph.maxLat - graph.minLat;
        double rangey = graph.maxLong - graph.minLong;
        double range = Math.max(rangex, rangey);

        for (Edge edge : graph.edges) {
            Node start = graph.nodes.get(edge.start);
            Node end = graph.nodes.get(edge.end);

            int lat1 = (int)(this.getHeight() - this.getHeight() * (start.latitude - graph.minLat) / range);
            int long1 = (int)(this.getWidth() * (start.longitude - graph.minLong) / range);
            int lat2 = (int)(this.getHeight() - this.getHeight() * (end.latitude - graph.minLat) / range);
            int long2 = (int)(this.getWidth() * (end.longitude - graph.minLong) / range);

            if (this.path.contains(edge.start + "-" + edge.end) || this.path.contains(edge.end + "-" + edge.start)) {
                g.setColor(Color.RED);
                g.drawLine(long1, lat1, long2, lat2);
                g.setColor(Color.BLACK);
            } else {
                g.drawLine(long1, lat1, long2, lat2);
            }
        }

    }

    private void dijkstra(Node start, Node end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        HashSet<String> visited = new HashSet<>();

        start.distance = 0;
        queue.add(start);

        while (!visited.contains(end.name)) {
            if (queue.isEmpty()) return;

            Node min = queue.poll();
            visited.add(min.name);

            for (Node adj: min.adjacent) {
                if (visited.contains(adj.name))
                    continue;

                double dist = min.distance + min.getDistance(adj);

                if (dist < adj.distance) {
                    adj.distance = dist;
                    adj.prev = min;
                    queue.add(adj);
                }
            }
        }
    }

    public void getShortestPath() {
        Node start = graph.nodes.get(this.start);
        Node end = graph.nodes.get(this.end);
        if (start == null || end == null) return;

        dijkstra(start, end);

        Node curr = end;
        while (curr != null) {
            if (curr.prev != null) {
                String edgeName = curr.prev.name + "-" + curr.name;
                this.path.add(edgeName);
                System.out.println(edgeName);
            }
            curr = curr.prev;
        }
    }
}
