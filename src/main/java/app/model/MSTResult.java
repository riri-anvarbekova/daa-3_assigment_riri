package app.model;

import java.util.List;
import app.algorithms.Metrics;

public class MSTResult {
    public List<Edge> mstEdges;
    public Metrics metrics;

    public MSTResult(List<Edge> mstEdges, Metrics metrics) {
        this.mstEdges = mstEdges;
        this.metrics = metrics;
    }
}
