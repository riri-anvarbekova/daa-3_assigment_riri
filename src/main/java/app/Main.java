package app;

import app.model.Graph;
import app.model.MSTResult;
import app.algorithms.PrimMST;
import app.algorithms.KruskalMST;
import app.io.GraphIO;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        Graph graph = GraphIO.readGraphFromJson("src/main/java/app/input/small_graphs.json");

        MSTResult primRes = PrimMST.prim(graph);
        MSTResult kruskalRes = KruskalMST.kruskal(graph);

        List<GraphIO.ResultEntry> results = new ArrayList<>();
        results.add(new GraphIO.ResultEntry(
                "SmallGraph",
                primRes.mstEdges,
                primRes.metrics.totalCost,                 // long, если у тебя int, можно кастить (long)primRes.metrics.totalCost
                primRes.metrics.operationCount,            // int
                (long) primRes.metrics.executionTimeMs,    // кастим double → long
                "Prim"
        ));

        results.add(new GraphIO.ResultEntry(
                "SmallGraph",
                kruskalRes.mstEdges,
                kruskalRes.metrics.totalCost,
                kruskalRes.metrics.operationCount,
                (long) kruskalRes.metrics.executionTimeMs,
                "Kruskal"
        ));

        GraphIO.writeGraphResultToJson("output/output.json", results);

        System.out.println("MST results saved to output/output.json");

        System.out.println("Prim MST Total Cost: " + primRes.metrics.totalCost);
        System.out.println("Kruskal MST Total Cost: " + kruskalRes.metrics.totalCost);
    }
}

