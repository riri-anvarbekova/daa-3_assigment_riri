package app.algorithms;

public class Metrics {
    public int numVertices;
    public int numEdges;
    public int totalCost;
    public double executionTimeMs;
    public int operationCount;


    public Metrics() {
        operationCount= 0;
        executionTimeMs = 0;
        totalCost = 0;
    }
}

