package app;

import app.model.Graph;
import app.model.MSTResult;
import app.algorithms.PrimeAlgorithm;
import app.algorithms.KruskalAlgorithm;
import app.io.GraphIO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Graph graph = GraphIO.readGraphFromJson("input/small_graph.json");

        MSTResult primRes = PrimeAlgorithm.prim(graph);
        MSTResult kruskalRes = KruskalAlgorithm.kruskal(graph);

        List<GraphIO.ResultEntry> results = new ArrayList<>();
        results.add(new GraphIO.ResultEntry("SmallGraph", primRes.mstEdges, primRes.metrics.totalCost,
                primRes.metrics.operationsCount, primRes.metrics.executionTimeMs, "Prim"));
        results.add(new GraphIO.ResultEntry("SmallGraph", kruskalRes.mstEdges, kruskalRes.metrics.totalCost,
                kruskalRes.metrics.operationsCount, kruskalRes.metrics.executionTimeMs, "Kruskal"));

        GraphIO.writeGraphResultToJson("output/output.json", results);

        System.out.println("MST results saved to output/output.json");
    }
}
