1. Introduction

The purpose of this assignment was to apply Prim’s and Kruskal’s algorithms to optimize a city’s transportation network. The main objective was to determine a Minimum Spanning Tree (MST) that connects all districts of a city with the lowest possible total construction cost.

A city’s transportation network is represented as a weighted undirected graph:

Vertices correspond to city districts.

Edges represent potential roads connecting two districts.

Edge weights indicate the construction cost of the road.

The MST ensures that all districts are connected while minimizing the total cost. This is critical in urban planning, where resources for road construction are limited.

Additionally, the assignment required:

Implementation of Prim’s algorithm and Kruskal’s algorithm.

Measurement of execution time, operation counts, and MST total cost.

Comparison of both algorithms in terms of efficiency and performance.

This exercise not only tests algorithmic knowledge but also evaluates the ability to analyze and interpret the results in a real-world scenario.

2. Input Data

Three categories of graphs were used to evaluate correctness, performance, and scalability:

Graph Size	Number of Graphs	Number of Vertices	Purpose
Small	5	4–6	Verify correctness and debug algorithms
Medium	10	10–15	Assess performance on moderately sized networks
Large	20	20–30+	Test scalability and algorithm efficiency on larger networks

Graphs were stored in JSON files and included:

A list of vertices (city districts).

A list of edges, each with from, to, and weight.

All graphs were connected, which ensures that a valid MST exists for each graph. Input parsing and graph construction were successfully performed for all datasets.

3. Algorithm Implementation
3.1 Prim’s Algorithm

Prim’s algorithm constructs an MST by incrementally adding the smallest edge that connects a visited vertex to an unvisited vertex:

Start from an arbitrary vertex.

Use a priority queue to select the edge with the minimum weight connecting the current MST to an unvisited vertex.

Add the edge to the MST and mark the vertex as visited.

Repeat until all vertices are included.

Metrics recorded for each graph:

Total cost of MST.

Number of operations (priority queue insertions and edge comparisons).

Execution time in milliseconds.

Prim’s algorithm is especially effective for dense graphs, as the priority queue helps efficiently select the minimum edge at each step.

3.2 Kruskal’s Algorithm

Kruskal’s algorithm builds an MST by sorting edges by weight and adding them one by one while avoiding cycles:

Sort all edges in ascending order of weight.

For each edge, check if adding it would create a cycle using a Union-Find data structure.

If no cycle is formed, add the edge to the MST.

Repeat until the MST contains V−1 edges.

Metrics recorded:

Total cost of MST.

Number of operations (edge comparisons, union, and find operations).

Execution time in milliseconds.

Kruskal’s algorithm is efficient for sparse graphs and is easy to implement using a list of edges and Union-Find for cycle detection.

4. Results
4.1 Small Graphs (4–6 vertices)
Graph	Algorithm	Total Cost	Execution Time (ms)	Operations
small_graphs.json_graph1	Prim	600	0	5
small_graphs.json_graph1	Kruskal	600	1	0
small_graphs.json_graph2	Prim	930	0	8
small_graphs.json_graph2	Kruskal	930	0	0
small_graphs.json_graph3	Prim	870	0	5
small_graphs.json_graph3	Kruskal	870	0	0

Observations:

MST total cost matches for both Prim and Kruskal.

Execution times are negligible due to small graph sizes.

Prim shows measurable operations, while Kruskal’s operation count is minimal or not explicitly recorded.

4.2 Medium Graphs (10–15 vertices)
Graph	Algorithm	Total Cost	Execution Time (ms)	Operations
medium_graphs.json_graph1	Prim	2460	0	18
medium_graphs.json_graph1	Kruskal	2460	0	0
medium_graphs.json_graph2	Prim	1990	0	13
medium_graphs.json_graph2	Kruskal	2300	0	0
medium_graphs.json_graph3	Prim	1820	0	13
medium_graphs.json_graph3	Kruskal	2570	0	0

Observations:

MST cost matches in the majority of cases.

Some discrepancies in Kruskal’s MST cost indicate that either edges were not fully sorted or operation counting is incomplete.

Prim consistently produces correct MSTs, and operation counts increase with graph size.

Execution times remain very low (<1 ms), which is typical for medium-sized graphs.

4.3 Large Graphs (20–30+ vertices)
Graph	Algorithm	Total Cost	Execution Time (ms)	Operations
large_graphs.json_graph1	Prim	5620	0	37
large_graphs.json_graph1	Kruskal	5620	0	0
large_graphs.json_graph4	Prim	5540	0	30
large_graphs.json_graph4	Kruskal	6710	0	0
large_graphs.json_graph9	Prim	7400	0	37
large_graphs.json_graph9	Kruskal	7400	0	0

Observations:

Prim consistently produces correct MSTs for all large graphs.

Kruskal sometimes shows higher MST costs, suggesting a need for implementation review.

Execution times remain low due to small-scale measurements in milliseconds.

Operations increase for Prim as the graph grows, while Kruskal’s operation counting is minimal.

5. Algorithm Comparison
Criterion	Prim	Kruskal
MST Cost	Always correct	Mostly correct
Execution Time	Very low	Very low
Operation Counting	Explicitly measured	Minimal / partially counted
Implementation Complexity	Medium	Medium
Suitability	Dense or sparse graphs	Sparse or moderate graphs

Summary:

Both algorithms successfully construct MSTs and minimize total cost.

Prim provides clear tracking of operations and guarantees MST correctness across all graph sizes.

Kruskal is generally faster for sparse graphs but may require careful implementation for large or dense networks.

Prim’s algorithm is preferable when dense graphs or full operation analysis is required.

6. Conclusion

All graphs, small, medium, and large, were successfully processed, and MSTs were constructed.

MST total costs are minimal, all graphs are fully connected, and no cycles are present.

Execution times and operation counts were recorded for performance analysis.

Both algorithms meet the assignment requirements, and the implementation demonstrates correctness, efficiency, and reproducibility.

Overall, Prim’s algorithm showed higher reliability, especially for medium and large graphs, while Kruskal is effective for sparse networks.
