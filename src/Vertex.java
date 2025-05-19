import java.util.*;

// Generic class to represent a graph vertex (node) with some data of type V
public class Vertex<V> {

    // The actual data stored in this vertex (e.g., "A", "B", or some object)
    private V data;

    // A map of adjacent (neighboring) vertices and the weights of edges to them
    private Map<Vertex<V>, Double> adjacentVertices;

    // Constructor: initializes the vertex with data and an empty adjacency list
    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    // Returns the data inside this vertex
    public V getData() {
        return data;
    }

    // Adds a neighbor to this vertex along with the weight of the edge
    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    // Returns the map of adjacent vertices and weights
    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    // Overriding equals to compare vertices based on their data (not memory address)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same object
        if (obj == null || getClass() != obj.getClass()) return false; // different type
        Vertex<?> vertex = (Vertex<?>) obj;
        return Objects.equals(data, vertex.data); // compare the data values
    }

    // Also override hashCode to be consistent with equals
    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    // Useful for printing the vertex (e.g., just prints "A" or "B")
    @Override
    public String toString() {
        return String.valueOf(data);
    }
}