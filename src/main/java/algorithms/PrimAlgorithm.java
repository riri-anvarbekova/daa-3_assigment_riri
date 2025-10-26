package algorithms;

import model.Edge;
import model.Graph;
import model.MSTMetrics;
import model.MSTResult;

import java.util.*;

public class PrimAlgorithm {

    public static MSTResult prim(Graph graph) {
        long start = System.nanoTime();

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0, operations = 0;

        String startNode = graph.vertices.get(0);
        visited.add(startNode);
        pq.addAll(graph.adjacencyList.get(startNode));

        while (!pq.isEmpty() && visited.size() < graph.vertices.size()) {
            Edge edge = pq.poll();
            operations++;

            String next = null;
            if (!visited.contains(edge.from)) next = edge.from;
            else if (!visited.contains(edge.to)) next = edge.to;

            if (next != null) {
                visited.add(next);
                mstEdges.add(edge);
                totalCost += edge.weight;

                for (Edge e : graph.adjacencyList.get(next)) {
                    if (!visited.contains(e.from) || !visited.contains(e.to)) {
                        pq.add(e);
                        operations++;
                    }
                }
            }
        }

        MSTResult res = new MSTResult(
                mstEdges,
                new MSTMetrics(totalCost, operations, (System.nanoTime() - start) / 1_000_000.0)
        );
        return res;
    }
}