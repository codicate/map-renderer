//Henry Liu
//32356861
//hliu68@u.rochester.edu

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StreetMap {
    public static void showMap(Panel panel) {
        SwingUtilities.invokeLater(() -> {
            panel.setBackground(Color.LIGHT_GRAY);
            var frame = new JFrame("Street Map");
            frame.setSize(1200, 1200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(args[0]);
        Scanner reader = new Scanner(inputFile);

        HashMap<String, Node> nodes = new HashMap<>();
        HashSet<Edge> edges = new HashSet<>();

        double minLat = 1000;
        double maxLat = -1000;
        double minLong = 1000;
        double maxLong = -1000;

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] segments = line.split("\t");

            if (segments[0].equals("i")) {
                String name = segments[1];
                double latitude = Double.parseDouble(segments[2]);
                double longitude = Double.parseDouble(segments[3]);

                if (latitude < minLat) minLat = latitude;
                if (latitude > maxLat) maxLat = latitude;
                if (longitude < minLong) minLong = longitude;
                if (longitude > maxLong) maxLong = longitude;

                Node node = new Node(segments[1], latitude, longitude);
                nodes.put(name, node);
            } else {
                String start = segments[2];
                String end = segments[3];
                nodes.get(start).adjacent.add(nodes.get(end));
                nodes.get(end).adjacent.add(nodes.get(start));
                Edge edge = new Edge(segments[1], start, end);
                edges.add(edge);
            }
        }

        Graph graph = new Graph(nodes, edges, maxLat, maxLong, minLat, minLong);

        boolean showMap = args.length > 1;
        boolean showPath = args.length > 2;

        Panel panel;
        if (showPath) {
            String start = args[args.length-2];
            String end = args[args.length-1];
            panel = new Panel(graph, start, end);
        } else {
            panel = new Panel(graph);
        }

        if (showMap) showMap(panel);
    }
}