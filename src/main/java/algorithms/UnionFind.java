package algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
    private Map<String, String> parent = new HashMap<>();

    public UnionFind(List<String> vertices) {
        for (String v : vertices) parent.put(v, v);
    }

    public String find(String v) {
        if (!parent.get(v).equals(v))
            parent.put(v, find(parent.get(v)));
        return parent.get(v);
    }

    public boolean union(String u, String v) {
        String rootU = find(u), rootV = find(v);
        if (rootU.equals(rootV)) return false;
        parent.put(rootU, rootV);
        return true;
    }
}
