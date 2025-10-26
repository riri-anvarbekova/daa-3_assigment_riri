package app.model;

public class MSTMetrics {
    public int totalCost;
    public int operationsCount;
    public double executionTimeMs;
    public int operations;
    public int execTimeMs;

    public MSTMetrics(int totalCost, int operationsCount, double executionTimeMs) {
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
    }

    @Override
    public String toString() {
        return "Cost=" + totalCost + ", Ops=" + operationsCount + ", Time=" + String.format("%.2f ms", executionTimeMs);
    }
}
