package algorithms;

import app.algorithms.PrimMST;
import app.io.GraphIO;
import app.model.Edge;
import app.model.Graph;
import app.model.MSTResult;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrimTest {

    @Test
    void testSmallGraph() throws IOException {
        Graph g = GraphIO.readGraphFromJson("src/main/java/app/input/small_graphs.json");
        MSTResult result = PrimMST.prim(g);

        assertNotNull(result.mstEdges, "MST не должно быть null");
        List<Edge> mst = result.mstEdges;

        assertEquals(g.vertices.size() - 1, mst.size(), "MST должно иметь V-1 рёбер");
        assertTrue(result.metrics.totalCost >= 0, "Общая стоимость MST >= 0");
        assertTrue(result.metrics.operationCount >= 0, "Количество операций >= 0");
        assertTrue(result.metrics.executionTimeMs >= 0, "Время выполнения >= 0");
    }

    @Test
    void testMediumGraph() throws IOException {
        Graph g = GraphIO.readGraphFromJson("src/main/java/app/input/medium_graphs.json");
        MSTResult result = PrimMST.prim(g);

        assertEquals(g.vertices.size() - 1, result.mstEdges.size());
        assertTrue(result.metrics.executionTimeMs >= 0);
    }

    @Test
    void testLargeGraph() throws IOException {
        Graph g = GraphIO.readGraphFromJson("src/main/java/app/input/large_graphs.json");
        MSTResult result = PrimMST.prim(g);

        assertEquals(g.vertices.size() - 1, result.mstEdges.size());
        assertTrue(result.metrics.executionTimeMs >= 0);
    }
}
