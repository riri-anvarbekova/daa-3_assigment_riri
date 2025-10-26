package app.utils;

import app.model.MSTResult;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;

public class MSTComparison {

    public static void writeCSV(String filePath, List<ComparisonEntry> entries) throws IOException {
        Path parentDir = Path.of(filePath).getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Graph,Algorithm,TotalCost,ExecutionTimeMs,Operations\n");
            for (ComparisonEntry e : entries) {
                writer.write(String.format("%s,%s,%d,%.2f,%d\n",
                        e.graphId, e.algorithm, e.totalCost, e.executionTimeMs, e.operations));
            }
        }
    }

    public static class ComparisonEntry {
        public String graphId;
        public String algorithm;
        public int totalCost;
        public double executionTimeMs;
        public long operations;

        public ComparisonEntry(String graphId, String algorithm, MSTResult res) {
            this.graphId = graphId;
            this.algorithm = algorithm;
            this.totalCost = res.totalCost;
            this.executionTimeMs = res.executionTimeMs;
            this.operations = res.operationCount;
        }
    }
}

