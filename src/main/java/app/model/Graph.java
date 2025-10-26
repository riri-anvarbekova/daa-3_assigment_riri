package app.model;

import java.util.*;

public class Graph {
    public List<String> vertices;
    public List<Edge> edges;
    public Map<String, List<Edge>> adjList;

    public Graph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        adjList = new HashMap<>();
    }

    public Graph(List<String> vertices, List<Edge> edges) {
        this();
        this.vertices.addAll(vertices);
        for (Edge e : edges) addEdge(e.from, e.to, e.weight);
    }

    public void addVertex(String v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            adjList.put(v, new ArrayList<>());
        }
    }

    public void addEdge(String from, String to, int weight) {
        Edge e = new Edge(from, to, weight);
        edges.add(e);
        adjList.get(from).add(e);
        adjList.get(to).add(new Edge(to, from, weight)); // неориентированный граф
    }
}



