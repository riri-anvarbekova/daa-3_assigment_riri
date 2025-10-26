package app.algorithms;

import app.model.Edge;
import app.model.Graph;
import app.model.MSTResult;

import java.util.*;

public class KruskalMST {

    static class UnionFind {
        private Map<String, String> parent = new HashMap<>();

        public UnionFind(List<String> vertices) {
            for (String v : vertices) parent.put(v, v);
        }

        public String find(String x, int[] operationCount) {
            operationCount[0]++;
            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x), operationCount));
            }
            return parent.get(x);
        }

        public boolean union(String x, String y, int[] operationCount) {
            String rootX = find(x, operationCount);
            String rootY = find(y, operationCount);
            operationCount[0]++;
            if (rootX.equals(rootY)) return false;
            parent.put(rootX, rootY);
            return true;
        }
    }

    public static MSTResult kruskal(Graph graph) {
        MSTResult result = new MSTResult();
        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int[] operationCount = new int[]{0};

        long startTime = System.nanoTime();

        UnionFind uf = new UnionFind(graph.vertices);

        List<Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges);
        operationCount[0] += sortedEdges.size();

        for (Edge e : sortedEdges) {
            if (uf.union(e.from, e.to, operationCount)) {
                mstEdges.add(e);
                totalCost += e.weight;
            }
        }

        long endTime = System.nanoTime();

        result.mstEdges = mstEdges;
        result.totalCost = totalCost;
        result.operationCount = operationCount[0];
        result.executionTimeMs = (endTime - startTime) / 1_000_000;

        return result;
    }
}