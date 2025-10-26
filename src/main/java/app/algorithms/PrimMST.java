package app.algorithms;

import app.model.Graph;
import app.model.Edge;
import app.model.MSTResult;

import java.util.*;

public class PrimMST {
    public static MSTResult prim(Graph graph) {
        MSTResult result = new MSTResult();
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operationCount = 0;

        long startTime = System.nanoTime();

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        String start = graph.vertices.get(0);
        visited.add(start);
        pq.addAll(graph.adjList.get(start));
        operationCount += pq.size();

        while (!pq.isEmpty() && visited.size() < graph.vertices.size()) {
            Edge e = pq.poll();
            operationCount++;

            if (visited.contains(e.to)) continue;

            visited.add(e.to);
            mstEdges.add(e);
            totalCost += e.weight;

            for (Edge next : graph.adjList.get(e.to)) {
                if (!visited.contains(next.to)) {
                    pq.add(next);
                    operationCount++;
                }
            }
        }

        long endTime = System.nanoTime();
        result.mstEdges = mstEdges;
        result.totalCost = totalCost;
        result.operationCount = operationCount;
        result.executionTimeMs = (endTime - startTime) / 1_000_000;

        return result;
    }
}
