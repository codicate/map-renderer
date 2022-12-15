# map-renderer

This project uses graph and djikstra's algorithm to render map from data file and find the shortest path between any two points.

### Usage

`input/[map].txt --show --directions [start] [end]`

`input/monroe.txt --show --directions i3232 i5942`

### Explaination

The program first reads through the map data file, and put all intersections into a hash table of nodes, and all street segments into a hash set of edges. Min/max latitude/longitude are recorded to make sure the map fit inside the display windows. We then simply need to loop through the edge, get the longitude and latitude of the start and end, and draw a line between them using Java graphics.

Before I draw the street segments, I first store the segments that makes up the shortest pathinto a hash set. This way, when looping through the edges, I can simply check if the current edge is in the hash set, and if it is, I draw it in red. I used djikstra's algorithm to calculate the shortest path, which will corrects updates the prev variables for each nodes up to the end node. Then we work our ways backward from end to start to store the shortest path into the hash set. 

### Images
![Screenshot_20221215_010335](https://user-images.githubusercontent.com/63321455/207787842-96db2585-65cf-49f2-88e9-5db13cbc62cc.png)
![Screenshot_20221215_010545](https://user-images.githubusercontent.com/63321455/207787849-0c0eb9bf-0388-4c29-9279-0acef6f69e45.png)
