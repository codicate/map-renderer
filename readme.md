# map-renderer

This project used graph and djikstra's algorithm to render a map from data points and find the shortest path between any two points.

### Usage
`input/[map].txt --show --directions [start] [end]`
`input/monroe.txt --show --directions i3232 i5942`

### Images

### Explaination

The program first reads through the map data file, and put all intersections into a hash table of nodes, and all street segments into a hash set of edges. Min/max latitude/longitude are recorded to make sure the map fit inside the display windows. We then simply need to loop through the edge, get the longitude and latitude of the start and end, and draw a line between them using Java graphics.

Before I draw the street segments, I first store the segments that makes up the shortest pathinto a hash set. This way, when looping through the edges, I can simply check if the current edge is in the hash set, and if it is, I draw it in red. I used djikstra's algorithm to calculate the shortest path, which will corrects updates the prev variables for each nodes up to the end node. Then we work our ways backward from end to start to store the shortest path into the hash set. 
