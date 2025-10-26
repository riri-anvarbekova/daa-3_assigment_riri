package app;

import app.algorithms.KruskalMST;
import app.algorithms.PrimMST;
import app.io.GraphIO;
import app.model.Graph;
import app.model.MSTResult;
import app.utils.MSTComparison;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GraphRunner {

    public static void main(String[] args) {
        try {
            Path outputDir = Path.of("output");
            if (!Files.exists(outputDir)) Files.createDirectories(outputDir);

            String[] inputFiles = {
                    "src/main/java/app/input/small_graphs.json",
                    "src/main/java/app/input/medium_graphs.json",
                    "src/main/java/app/input/large_graphs.json"
            };

            List<MSTComparison.ComparisonEntry> csvResults = new ArrayList<>();

            for (String filePath : inputFiles) {
                List<Graph> graphs = GraphIO.readGraphsFromJson(filePath);

                for (int i = 0; i < graphs.size(); i++) {
                    Graph g = graphs.get(i);
                    String graphName = Path.of(filePath).getFileName() + "_graph" + (i + 1);

                    MSTResult primResult = PrimMST.prim(g);
                    MSTResult kruskalResult = KruskalMST.kruskal(g);

                    csvResults.add(new MSTComparison.ComparisonEntry(graphName, "Prim", primResult));
                    csvResults.add(new MSTComparison.ComparisonEntry(graphName, "Kruskal", kruskalResult));
                }
            }

            MSTComparison.writeCSV(outputDir.resolve("output.csv").toString(), csvResults);

            System.out.println("Обработка завершена. Результаты сохранены в output/output.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


