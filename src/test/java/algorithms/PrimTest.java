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
    void testSmallGraphs() throws IOException {
        List<Graph> graphs = GraphIO.readGraphsFromJson("src/main/java/app/input/small_graphs.json");
        for (Graph g : graphs) {
            MSTResult result = PrimMST.prim(g);
            List<Edge> mst = result.mstEdges;
            assertNotNull(mst);
            assertEquals(g.vertices.size() - 1, mst.size());
            assertTrue(result.metrics.totalCost >= 0);
            assertTrue(result.metrics.operationCount >= 0);
            assertTrue(result.metrics.executionTimeMs >= 0);
        }
    }

    @Test
    void testMediumGraphs() throws IOException {
        List<Graph> graphs = GraphIO.readGraphsFromJson("src/main/java/app/input/medium_graphs.json");
        for (Graph g : graphs) {
            MSTResult result = PrimMST.prim(g);
            assertEquals(g.vertices.size() - 1, result.mstEdges.size());
            assertTrue(result.metrics.executionTimeMs >= 0);
        }
    }

    @Test
    void testLargeGraphs() throws IOException {
        List<Graph> graphs = GraphIO.readGraphsFromJson("src/main/java/app/input/large_graphs.json");
        for (Graph g : graphs) {
            MSTResult result = PrimMST.prim(g);
            assertEquals(g.vertices.size() - 1, result.mstEdges.size());
            assertTrue(result.metrics.executionTimeMs >= 0);
        }
    }
}
