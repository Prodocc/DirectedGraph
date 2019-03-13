
import java.util.*;

class DirectedGraph {

    private Map<String, Vertex> vertexes;

    public DirectedGraph(Map<String, Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    Map<String, Vertex> getVertexes() {
        return vertexes;
    }

    public class Vertex {
        private String name;
        private List<Edge> edges;

        Vertex(String name, List<Edge> edges) {
            this.name = name;
            this.edges = edges;
        }

        List<Edge> getEdges() {
            return edges;
        }

        private void setName(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "name='" + name + '\'' +
                    ", edges=" + edges +
                    '}';
        }
    }

    public static class Edge {
        private int weight;
        private String direction;

        String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        private void setWeight(int weight) {
            this.weight = weight;
        }

        int getWeight() {
            return weight;
        }


        Edge(int weight, String direction) {
            this.weight = weight;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "weight=" + weight +
                    ", direction='" + direction + '\'' +
                    '}';
        }
    }


    boolean addVertex(String name) {
        if (hasVertex(name)) {
            System.out.println("Graph already has the same Vertex");
            return false;
        } else {
            vertexes.put(name, new Vertex(name, new ArrayList<>()));
            System.out.println("Vertex " + name + " has been added");
        }
        return true;
    }

    private boolean hasVertex(String name) {
        return vertexes.containsKey(name);
    }

    boolean addEdge(String name, int weight, String direction) {
        if (!hasVertex(direction)) {
            System.out.println("Graph doesn't contain vertex " + direction);
            return false;
        } else {
            for (Map.Entry<String, Vertex> entry : vertexes.entrySet()) {
                if (entry.getValue().equals(getVertex(name))) {
                    entry.getValue().edges.add(new Edge(weight, direction));
                }
            }
            System.out.println("Edge has been added from Vertex " + name + " to Vertex " + direction);
        }
        return true;
    }


    boolean removeVertex(String name) {
        if (!hasVertex(name)) {
            System.out.println("Graph can't delete a nonexistent vertex");
            return false;
        } else {
            for (Map.Entry<String, Vertex> entry : vertexes.entrySet()) {
                for (Edge edge : entry.getValue().edges) {
                    if (edge.direction.equals(name)) {
                        edge.direction = "";
                    }
                }
            }
            vertexes.remove(name, getVertex(name));
        }
        return true;
    }

    boolean removeEdge(String startVertex, String endVertex) {
        getVertex(startVertex).edges.remove(getEdge(startVertex, endVertex));
        System.out.println("Edge between Vertex " + startVertex + " and Vertex " + endVertex + " has been removed");
        return true;
    }

    boolean changeName(String name, String changedName) {
        vertexes.put(changedName, vertexes.get(name));
        vertexes.remove(name);
        vertexes.get(changedName).setName(changedName);
        for (Map.Entry<String, Vertex> entry : vertexes.entrySet()) {
            for (Edge edge : entry.getValue().edges) {
                if (edge.direction.equals(name)) {
                    edge.direction = changedName;
                }
            }
        }
        return true;
    }

    int changeWeight(String startVertex, String endVertex, int weight) {
        Edge edge = null;
        for (Edge edges : getVertex(startVertex).edges) {
            if (edges.direction.equals(endVertex)) {
                edge = edges;
            }
        }
        //int oldWeight = edge.getWeight();
        edge.setWeight(weight);
        System.out.println("The Edge weight between Vertex " + startVertex + " and Vertex " + endVertex + " has been changed");
        return edge.getWeight();
    }

    Vertex getVertex(String name) {
        for (Map.Entry<String, Vertex> entry : vertexes.entrySet()) {
            if (entry.getValue().name.equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private Edge getEdge(String vertexName, String direction) {
        for (Map.Entry<String, Vertex> entry : vertexes.entrySet()) {
            if (entry.getValue().equals(getVertex(vertexName))) {
                for (Edge edge : entry.getValue().edges) {
                    if (edge.getDirection().equals(direction)) {
                        return edge;
                    }
                }
            }
        }
        return null;
    }

    List<Edge> getOutGoingEdges(String vertexName) {
        return getVertex(vertexName).edges;

    }

    List<Edge> getIncomingEdges(String vertexName) {
        List<Edge> list = new LinkedList<>();
        for (Map.Entry<String, Vertex> entry : vertexes.entrySet()) {
            for (Edge edge : entry.getValue().edges) {
                if (edge.direction.equals(vertexName)) {
                    list.add(edge);
                }
            }
        }
        return list;
    }

    void showVertexMap() {
        for (Map.Entry<String, Vertex> entry : vertexes.entrySet()) {
            System.out.print(entry.getValue().name + " -> " + getEdge(entry.getValue().name));
            System.out.println();
        }
    }


    private String getEdge(String name) {//Vertex name
        StringBuilder sb = new StringBuilder();
        Vertex vertex = getVertex(name);
        for (Edge edge : vertex.edges) {
            sb.append(edge.direction);
            sb.append("(weight of edge = ").append(edge.weight).append(") ");
            if (vertex.edges.indexOf(edge) != vertex.edges.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

}
