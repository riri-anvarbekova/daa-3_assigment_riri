package app.io;

import app.model.Edge;
import app.model.Graph;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GraphIO {

    // ------------------- Чтение графа из JSON -------------------
    public static Graph readGraphFromJson(String path) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(path)));
        JSONArray graphsArray = new JSONArray(content);

        if (graphsArray.length() == 0) {
            throw new IllegalArgumentException("JSON массив пустой: " + path);
        }

        JSONObject graphObject = graphsArray.getJSONObject(0);
        Graph g = new Graph();

        // Добавляем вершины
        JSONArray nodes = graphObject.getJSONArray("nodes");
        for (int i = 0; i < nodes.length(); i++) {
            g.addVertex(nodes.getString(i));
        }

        // Добавляем рёбра
        JSONArray edges = graphObject.getJSONArray("edges");
        for (int i = 0; i < edges.length(); i++) {
            JSONObject edge = edges.getJSONObject(i);
            String from = edge.getString("from");
            String to = edge.getString("to");
            int weight = edge.getInt("weight");
            g.addEdge(from, to, weight);
        }

        return g;
    }

    // ------------------- Запись результатов MST в JSON -------------------
    public static void writeGraphResultToJson(String path, List<ResultEntry> results) throws IOException {
        // Создание папки, если её нет
        File file = new File(path);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        JSONArray jsonResults = new JSONArray();

        for (ResultEntry r : results) {
            JSONObject graphJson = new JSONObject();
            graphJson.put("graphName", r.graphName);
            graphJson.put("algorithm", r.algorithm);
            graphJson.put("totalCost", r.totalCost);
            graphJson.put("operations", r.operations);
            graphJson.put("executionTimeMs", r.execTimeMs);

            JSONArray edgesJson = new JSONArray();
            if (r.edges != null) {
                for (Edge e : r.edges) {
                    JSONObject edgeJson = new JSONObject();
                    edgeJson.put("from", e.from);
                    edgeJson.put("to", e.to);
                    edgeJson.put("weight", e.weight);
                    edgesJson.put(edgeJson);
                }
            }
            graphJson.put("edges", edgesJson);

            jsonResults.put(graphJson);
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonResults.toString(4));
        }
    }

    // ------------------- Внутренний класс ResultEntry -------------------
    public static class ResultEntry {
        public String graphName;
        public List<Edge> edges;
        public long totalCost;
        public int operations;
        public long execTimeMs;
        public String algorithm;

        public ResultEntry(String graphName, List<Edge> edges, long totalCost, int operations, long execTimeMs, String algorithm) {
            this.graphName = graphName;
            this.edges = edges;
            this.totalCost = totalCost;
            this.operations = operations;
            this.execTimeMs = execTimeMs;
            this.algorithm = algorithm;
        }
    }
}
