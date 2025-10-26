package app.algorithms;

import app.model.Edge;
import app.model.Graph;
import app.model.MSTResult;

import java.util.*;

public class PrimMST {

    public static MSTResult prim(Graph graph) {
        Metrics metrics = new Metrics();
        metrics.numVertices = graph.vertices.size();
        metrics.numEdges = graph.edges.size();

        long startTime = System.currentTimeMillis();

        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        String start = graph.vertices.get(0);
        visited.add(start);
        pq.addAll(graph.adjList.get(start));

        while (!pq.isEmpty() && visited.size() < graph.vertices.size()) {
            metrics.operationCount++;
            Edge e = pq.poll();
            if (visited.contains(e.to)) continue;

            visited.add(e.to);
            mstEdges.add(e);
            metrics.totalCost += e.weight;

            for (Edge next : graph.adjList.get(e.to)) {
                if (!visited.contains(next.to)) {
                    pq.add(next);
                    metrics.operationCount++;
                }
            }
        }

        metrics.executionTimeMs = System.currentTimeMillis() - startTime;

        if (mstEdges.size() != graph.vertices.size() - 1) {
            System.out.println("Graph is disconnected, MST not complete");
        }

        return new MSTResult(mstEdges, metrics);
    }
}
