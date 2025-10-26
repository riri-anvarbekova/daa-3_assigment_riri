package app.io;

import app.model.Edge;
import app.model.Graph;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.json.*;
import org.json.JSONObject;
import org.json.JSONArray;

public class GraphIO {

    public static Graph readGraphFromJson(String filePath) throws IOException {
        Graph graph = new Graph();
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject json = new JSONObject(content);

        JSONArray nodes = json.getJSONArray("nodes");
        for (int i = 0; i < nodes.length(); i++) {
            graph.addVertex(nodes.getString(i));
        }

        JSONArray edges = json.getJSONArray("edges");
        for (int i = 0; i < edges.length(); i++) {
            JSONObject e = edges.getJSONObject(i);
            graph.addEdge(e.getString("from"), e.getString("to"), e.getInt("weight"));
        }

        return graph;
    }

    public static void writeGraphResultToJson(String filePath, List<ResultEntry> results) throws IOException {
        JSONObject outJson = new JSONObject();
        JSONArray arr = new JSONArray();
        for (ResultEntry r : results) arr.put(r.toJson());
        outJson.put("results", arr);
        Files.write(Paths.get(filePath), outJson.toString(4).getBytes());
    }

    public static class ResultEntry {
        public String graphId;
        public List<Edge> mstEdges;
        public int totalCost;
        public long operations;
        public double execTimeMs;
        public String algorithm;

        public ResultEntry(String graphId, List<Edge> mstEdges, int totalCost, long operations, double execTimeMs, String algorithm) {
            this.graphId = graphId;
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operations = operations;
            this.execTimeMs = execTimeMs;
            this.algorithm = algorithm;
        }

        public JSONObject toJson() {
            JSONObject obj = new JSONObject();
            obj.put("graph_id", graphId);
            obj.put("algorithm", algorithm);
            obj.put("total_cost", totalCost);
            obj.put("operations", operations);
            obj.put("execution_time_ms", execTimeMs);

            JSONArray edgesArr = new JSONArray();
            for (Edge e : mstEdges) {
                JSONObject edge = new JSONObject();
                edge.put("from", e.from);
                edge.put("to", e.to);
                edge.put("weight", e.weight);
                edgesArr.put(edge);
            }
            obj.put("mst_edges", edgesArr);
            return obj;
        }
    }
}
