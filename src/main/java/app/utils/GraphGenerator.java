package app.utils;

import app.model.Graph;

public class GraphGenerator {

    public static Graph generateRandomGraph(int verticesCount, int maxWeight) {
        Graph g = new Graph();
        for (int i = 0; i < verticesCount; i++) {
            g.addVertex("V" + i);
        }
        for (int i = 0; i < verticesCount; i++) {
            for (int j = i + 1; j < verticesCount; j++) {
                int weight = (int) (Math.random() * maxWeight) + 1;
                g.addEdge("V" + i, "V" + j, weight);
            }
        }
        return g;
    }
}
