package app.io;

import app.model.Graph;
import app.model.Edge;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class GraphIO {

    public static List<Graph> readGraphsFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(filePath));
        List<Graph> graphs = new ArrayList<>();

        for (JsonNode graphNode : root) {
            Graph g = new Graph();
            for (JsonNode v : graphNode.get("vertices")) g.addVertex(v.asText());
            for (JsonNode e : graphNode.get("edges")) {
                g.addEdge(e.get("from").asText(), e.get("to").asText(), e.get("weight").asInt());
            }
            graphs.add(g);
        }
        return graphs;
    }

    public static void writeGraphsResultToJson(String filePath, Object data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }
}

