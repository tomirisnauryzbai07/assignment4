import java.util.*;

public class BFS<V> implements Search<V> {
    // Maps to store visited vertices and their predecessors
    private Map<V, Vertex<V>> visitedVertices = new HashMap<>(); // Stores the visited vertices
    private Map<V, V> predecessors = new HashMap<>(); // Stores the predecessors for each vertex
    private V startVertex; // The starting vertex for the BFS

    // Constructor: Initializes the BFS and starts the search from the start vertex
    public BFS(WeightedGraph<V> graph, V start) {
        this.startVertex = start;
        bfs(graph, start); // Calls the BFS algorithm on the graph starting from 'start'
    }

    // BFS algorithm to traverse the graph level by level
    private void bfs(WeightedGraph<V> graph, V start) {
        Queue<Vertex<V>> queue = new LinkedList<>(); // Queue to hold vertices to be explored (FIFO)
        Set<V> visited = new HashSet<>(); // Set to track visited vertices to avoid revisiting

        // Get the starting vertex object from the graph
        Vertex<V> startVertexObj = graph.getVertex(start);
        if (startVertexObj == null) return; // If the starting vertex is not found, exit

        // Add the starting vertex to the queue and mark it as visited
        queue.add(startVertexObj);
        visited.add(start);

        // Begin BFS traversal
        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll(); // Remove the vertex from the front of the queue
            visitedVertices.put(current.getData(), current); // Mark the current vertex as visited

            // Explore all neighboring vertices of the current vertex
            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                V neighborData = neighbor.getData();
                // If the neighbor hasn't been visited, visit it
                if (!visited.contains(neighborData)) {
                    visited.add(neighborData); // Mark neighbor as visited
                    predecessors.put(neighborData, current.getData()); // Record the path
                    queue.add(neighbor); // Add neighbor to the queue for further exploration
                }
            }
        }
    }

    // Reconstruct the path from the start vertex to the destination vertex
    @Override
    public List<Vertex<V>> getPath(V destination) {
        List<Vertex<V>> path = new LinkedList<>(); // List to store the final path from start to destination
        V current = destination; // Start with the destination vertex

        // Trace back the path from destination to start using the predecessors map
        while (current != null && !current.equals(startVertex)) {
            Vertex<V> vertex = visitedVertices.get(current); // Get the vertex object for the current data
            if (vertex == null) return Collections.emptyList(); // If vertex is not found, return an empty path
            path.add(0, vertex); // Add the vertex to the path (reverse the order)
            current = predecessors.get(current); // Move to the predecessor vertex
        }

        // Add the start vertex to the path if it exists
        Vertex<V> startVertexObj = visitedVertices.get(startVertex);
        if (startVertexObj != null) {
            path.add(0, startVertexObj); // Add start vertex at the beginning of the path
        }

        return path; // Return the final path
    }
}