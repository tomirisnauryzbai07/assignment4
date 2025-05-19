import java.util.*;

// This interface is used for any search algorithm (like BFS, Dijkstra, etc.)
public interface Search<V> {
    // Method to get the path from the starting vertex to the destination vertex.
    // It returns a list of vertices representing the path from start to destination.
    List<Vertex<V>> getPath(V destination);
}