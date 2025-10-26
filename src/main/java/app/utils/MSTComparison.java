package app.utils;

import app.model.MSTResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MSTComparison {

    public static void writeCSV(String filePath, List<ComparisonEntry> entries) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write("Graph,Algorithm,TotalCost,ExecutionTimeMs,Operations\n");
        for (ComparisonEntry e : entries) {
            writer.write(String.format("%s,%s,%d,%.2f,%d\n",
                    e.graphId, e.algorithm, e.totalCost, e.executionTimeMs, e.operations));
        }
        writer.close();
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
            this.totalCost = res.metrics.totalCost;
            this.executionTimeMs = res.metrics.executionTimeMs;
            this.operations = res.metrics.operationsCount;
        }
    }
}
