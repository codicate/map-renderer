# map-renderer

CSC172 Project 3 - Street Mapping

In this project, we used graph and djikstra's algorithm to find the shortest path between two points on a map. The map
is represented by a graph, where each node is a street intersection and each edge is a street segment. The weight of
each edge is the distance between start to the current intersection.

The program works by first reading through the map data file, and putting all the intersections into a hash table of
nodes, and all the street segments into a hash set of edges. Min/max latitude/longitude are recorded so that later on
the map can be draw so that it fits inside the display windows. After we have the hash set of edges, we simply need to
loop through the edge, get the longitude and latitude of the start and end of the edge, and draw a line between them
using Java graphics.

For calculating the shortest path between two points, I did something clever that I store the shortest path, segment by
segment, into a hash set first before I draw the rest of the street segments. This way, in the Java graphics, I can just
check if the current edge is in the hash set, and if it is, I draw it in red. This way, the shortest path is always drawn

To calculate the shortest path, I used djikstra's algorithm. I first put the start node into a priority queue, and set
the distance of the start node to 0. All other node distance is initialized to infinity and prev is null. Then I loop
through the priority queue, and for each node, I loop through all the edges that are adjacent to the node, and for
each edge, I check if the distance of the end node is less than the distance of the start node plus the weight of the
edge. If it is, I update the distance of the end node to the distance of the start node plus the weight of the edge, and
set the prev of the end node to the start node. I also put the end node into the priority queue.

After I finish looping through the priority queue, I simply loop through the end node's prev, and add the edge to the
hash set. Then I draw the rest of the edges. We kinda first work forward from start to end for the djistra's algorithm,
and then work backward from end to start to store the shortest path into the hash set.

The program works for all the maps, and most of the random points I tried. I noticed for some points, the program does
not work, such as monroe country i10. I believe this is because the map data is not complete, and there are some
intersections that are not connected to the rest of the street segments, but it's hard to validate this. Other pairs of
intersections spanning thousands of other interactions work fast and flawlessly.
