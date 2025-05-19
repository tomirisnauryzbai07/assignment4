import java.util.*;

// Represents a weighted graph, where each vertex is connected by weighted edges
public class WeightedGraph<V> {

    // Map storing vertices by their data. Each vertex has a list of adjacent vertices.
    private Map<V, Vertex<V>> vertices;

    // Constructor to initialize the graph with an empty set of vertices
    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    // Adds a new vertex to the graph with the given data (vertex value)
    public void addVertex(V vertex) {
        // Creates a new Vertex object and puts it in the map
        vertices.put(vertex, new Vertex<>(vertex));
    }

    // Adds an edge between two vertices (source and destination) with a given weight
    public void addEdge(V sourceData, V destData, double weight) {
        // Retrieve the source and destination vertices using the provided data
        Vertex<V> source = vertices.get(sourceData);
        Vertex<V> dest = vertices.get(destData);

        // Throw an exception if either source or destination does not exist
        if (source == null || dest == null) {
            throw new IllegalArgumentException("Source and destination vertex are null!");
        }

        // Add the destination vertex as an adjacent vertex of the source
        source.addAdjacentVertex(dest, weight);
        // If the graph is undirected, also add the source vertex as an adjacent vertex of the destination
        dest.addAdjacentVertex(source, weight);
    }

    // Gets a vertex by its data (value)
    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    // Returns a collection of all vertices in the graph
    public Collection<Vertex<V>> getVertices() {
        return vertices.values();
    }
}