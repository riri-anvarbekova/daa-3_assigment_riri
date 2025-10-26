package model;
import java.util.List;

public class MSTResult {
    public List<Edge> mstEdges;
    public MSTMetrics metrics;

    public MSTResult(List<Edge> mstEdges, MSTMetrics metrics) {
        this.mstEdges = mstEdges;
        this.metrics = metrics;
    }
}
