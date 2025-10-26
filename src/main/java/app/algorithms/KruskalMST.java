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

        public String find(String x) {
            if (!parent.get(x).equals(x)) parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        public boolean union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);
            if (rootX.equals(rootY)) return false;
            parent.put(rootX, rootY);
            return true;
        }
    }

    public static MSTResult kruskal(Graph graph) {
        Metrics metrics = new Metrics();
        metrics.numVertices = graph.vertices.size();
        metrics.numEdges = graph.edges.size();

        long startTime = System.currentTimeMillis();
        List<Edge> mstEdges = new ArrayList<>();
        UnionFind uf = new UnionFind(graph.vertices);

        List<Edge> sortedEdges = new ArrayList<>(graph.edges);
        Collections.sort(sortedEdges);

        for (Edge e : sortedEdges) {
            if (uf.union(e.from, e.to)) {
                mstEdges.add(e);
                metrics.totalCost += e.weight;
            }
        }

        metrics.executionTimeMs = System.currentTimeMillis() - startTime;
        return new MSTResult(mstEdges, metrics);
    }
}
