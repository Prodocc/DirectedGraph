import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {
    private DirectedGraph graph = new DirectedGraph(new HashMap<>());

    @Test
    @Tag("Test")
    void test() {

    }

    @Test
    @Tag("addVertex")
    void addVertex() {
        graph.addVertex("first");
        assertTrue(graph.getVertexes().containsValue(graph.getVertex("first")));

    }

    @Test
    @Tag("addEdge")
    void addEdge() {
        graph.addVertex("first");
        graph.addVertex("second");
        graph.addEdge("first", 1, "second");
        for (Map.Entry<String, DirectedGraph.Vertex> entry : graph.getVertexes().entrySet()) {
            if (entry.getValue().equals(graph.getVertex("first"))) {
                for (DirectedGraph.Edge edge : entry.getValue().getEdges()) {
                    if (!edge.getDirection().equals("second")) {
                        fail();
                    }
                }
            }
        }
    }

    @Test
    @Tag("removeVertex")
    void removeVertex() {
        graph.addVertex("first");
        graph.addVertex("second");
        graph.addEdge("second", 1, "first");
        graph.removeVertex("first");
        boolean vertexStringContain = graph.getVertexes().containsKey("first");
        boolean vertexInListContain = graph.getVertexes().containsValue(graph.getVertex("first"));
        for (Map.Entry<String, DirectedGraph.Vertex> entry : graph.getVertexes().entrySet()) {
            for (DirectedGraph.Edge edge : entry.getValue().getEdges()) {
                if (edge.getDirection().equals("first") || vertexStringContain
                        || vertexInListContain) {
                    fail();
                }
            }
        }
    }

    @Test
    @Tag("removeEdge")
    void removeEdge() {
        graph.addVertex("first");
        graph.addVertex("second");
        graph.addEdge("first", 1, "second");
        graph.removeEdge("first", "second");
        for (DirectedGraph.Edge edge : graph.getVertex("first").getEdges()) {
            if (edge.getDirection().equals("second")) {
                fail();
            }
        }
    }

    @Test
    @Tag("getOutGoingEdges")
    void getOutGoingEdges() {
        graph.addVertex("first");
        graph.addVertex("second");
        graph.addVertex("third");
        graph.addEdge("first", 1, "second");
        graph.addEdge("first", 2, "third");
        List<DirectedGraph.Edge> list = graph.getOutGoingEdges("first");
        List<DirectedGraph.Edge> testList = new LinkedList<>();
        testList.add(new DirectedGraph.Edge(1, "second"));
        testList.add(new DirectedGraph.Edge(2, "third"));
        if (testList.size() == list.size()) {
            int i = 0;
            while (i != testList.size()) {
                if (!testList.get(i).getDirection().
                        equals(list.get(i).getDirection())
                        || testList.get(i).getWeight() != list.get(i).getWeight()) {
                    fail();
                }
                i++;
            }
        }
    }

    @Test
    @Tag("getIncomingEdges")
    void getIncomingEdges() {
        graph.addVertex("first");
        graph.addVertex("second");
        graph.addVertex("third");
        graph.addEdge("first", 1, "third");
        graph.addEdge("second", 2, "third");
        List<DirectedGraph.Edge> list = graph.getIncomingEdges("third");
        List<DirectedGraph.Edge> testList = new LinkedList<>();
        testList.add(new DirectedGraph.Edge(1, "third"));
        testList.add(new DirectedGraph.Edge(2, "third"));
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getDirection().equals(testList.get(i).getDirection())
                    || list.get(i).getWeight() != testList.get(i).getWeight()) {
                fail();
            }
        }
    }

    @Test
    @Tag("changeName")
    void changeName() {
        graph.addVertex("first");
        graph.changeName("first", "первый");
        assertTrue(checkName("первый"));
    }

    private boolean checkName(String name) {
        return graph.getVertex(name).getName().equals(name);
    }

    @Test
    @Tag("changeWeight")
    void changeWeight() {
        graph.addVertex("first");
        graph.addVertex("second");
        graph.addEdge("first", 1, "second");
        assertEquals(2, graph.changeWeight("first", "second", 2));
    }


}