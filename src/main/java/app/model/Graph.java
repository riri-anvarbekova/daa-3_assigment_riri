package app.model;

import java.util.*;

public class Graph {
    public List<String> vertices = new ArrayList<>();
    public List<Edge> edges = new ArrayList<>();
    public Map<String, List<Edge>> adjacencyList = new HashMap<>();

    public void addVertex(String v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            adjacencyList.put(v, new ArrayList<>());
        }
    }

    public void addEdge(String from, String to, int weight) {
        Edge e = new Edge(from, to, weight);
        edges.add(e);
        adjacencyList.get(from).add(e);
        adjacencyList.get(to).add(e);
    }
}

