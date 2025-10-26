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
        this.vertices = vertices;
        this.edges = edges;
        adjList = new HashMap<>();
        for (String v : vertices) adjList.put(v, new ArrayList<>());
        for (Edge e : edges) {
            adjList.get(e.from).add(e);
            adjList.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }
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
        adjList.get(to).add(new Edge(to, from, weight));
    }

    public boolean isAcyclic(List<Edge> mst) {
        Map<String, String> parent = new HashMap<>();
        for (String v : vertices) parent.put(v, v);

        for (Edge e : mst) {
            String root1 = find(parent, e.from);
            String root2 = find(parent, e.to);
            if (root1.equals(root2)) return false;
            parent.put(root1, root2);
        }
        return true;
    }

    private String find(Map<String, String> parent, String v) {
        if (!parent.get(v).equals(v)) parent.put(v, find(parent, parent.get(v)));
        return parent.get(v);
    }

    public boolean isConnectedMST(List<Edge> mst) {
        if (mst.size() != vertices.size() - 1) return false;
        Map<String, String> parent = new HashMap<>();
        for (String v : vertices) parent.put(v, v);

        for (Edge e : mst) {
            String root1 = find(parent, e.from);
            String root2 = find(parent, e.to);
            parent.put(root1, root2);
        }

        String root = find(parent, vertices.get(0));
        for (String v : vertices) {
            if (!find(parent, v).equals(root)) return false;
        }
        return true;
    }
}


