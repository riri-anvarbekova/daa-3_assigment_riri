package app.algorithms;

import app.model.Edge;
import app.model.Graph;
import app.model.MSTMetrics;
import app.model.MSTResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    public static MSTResult kruskal(Graph graph) {
        long start = System.nanoTime();

        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges);

        UnionFind uf = new UnionFind(graph.vertices);

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0, operations = 0;

        for (Edge e : edges) {
            operations++;
            if (uf.union(e.from, e.to)) {
                mstEdges.add(e);
                totalCost += e.weight;
            }
        }

        MSTResult res = new MSTResult(
                mstEdges,
                new MSTMetrics(totalCost, operations, (System.nanoTime() - start) / 1_000_000.0)
        );
        return res;
    }
}
