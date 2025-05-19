import java.util.*;

public class DijkstraSearch<V> implements Search<V> {

    // Maps to store distances and predecessors for each vertex
    private Map<V, Double> distances = new HashMap<>();  // Stores the shortest distance to each vertex
    private Map<V, V> predecessors = new HashMap<>();     // Stores the predecessor of each vertex for path reconstruction
    private Map<V, Vertex<V>> vertexMap = new HashMap<>(); // Maps vertex data to actual Vertex objects
    private V startVertex; // The starting vertex

    // Constructor: Initializes the Dijkstra search and starts the computation
    public DijkstraSearch(WeightedGraph<V> graph, V start) {
        this.startVertex = start;
        // Initialize the vertex map
        for (Vertex<V> vertex : graph.getVertices()) {
            vertexMap.put(vertex.getData(), vertex);
        }
        // Compute the shortest paths from the start vertex
        computeShortestPaths(start);
    }

    // Dijkstra's algorithm to compute the shortest paths from the start vertex
    private void computeShortestPaths(V start) {
        // Priority queue to explore vertices based on the shortest known distance (min-heap)
        PriorityQueue<VertexDistance<V>> priorityQueue = new PriorityQueue<>();

        // Initially, set the distance to the start vertex as 0
        distances.put(start, 0.0);
        priorityQueue.add(new VertexDistance<>(start, 0.0));

        // Explore all vertices using the priority queue
        while (!priorityQueue.isEmpty()) {
            // Get the vertex with the smallest distance
            VertexDistance<V> current = priorityQueue.poll();
            V currentData = current.vertex;
            Vertex<V> currentVertex = vertexMap.get(currentData);

            // If currentVertex is null, continue to the next iteration
            if (currentVertex == null) continue;

            // Explore all adjacent vertices of the current vertex
            for (Map.Entry<Vertex<V>, Double> entry : currentVertex.getAdjacentVertices().entrySet()) {
                V neighborData = entry.getKey().getData(); // Neighbor vertex data
                double edgeWeight = entry.getValue(); // Edge weight
                double newDist = distances.get(currentData) + edgeWeight; // Calculate new potential distance

                // If the new distance is shorter, update the distance and predecessor
                if (newDist < distances.getOrDefault(neighborData, Double.POSITIVE_INFINITY)) {
                    distances.put(neighborData, newDist);
                    predecessors.put(neighborData, currentData);
                    priorityQueue.add(new VertexDistance<>(neighborData, newDist));
                }
            }
        }
    }

    // Method to reconstruct and return the shortest path from start to destination
    @Override
    public List<Vertex<V>> getPath(V destination) {
        List<Vertex<V>> path = new LinkedList<>();
        V current = destination;

        // Trace back the path from destination to start using the predecessors map
        while (current != null && !current.equals(startVertex)) {
            Vertex<V> vertex = vertexMap.get(current);
            if (vertex == null) return Collections.emptyList(); // If vertex is not found, return empty path
            path.add(0, vertex); // Add vertex to the path (reversed order)
            current = predecessors.get(current); // Move to the predecessor vertex
        }

        // Add the start vertex to the path if it exists
        Vertex<V> startVertexObj = vertexMap.get(startVertex);
        if (startVertexObj != null) {
            path.add(0, startVertexObj); // Add start vertex at the beginning of the path
        }

        return path; // Return the reconstructed path
    }

    // Helper class to store vertex and its associated distance for priority queue
    private static class VertexDistance<V> implements Comparable<VertexDistance<V>> {
        V vertex;
        double distance;

        // Constructor to create a new VertexDistance object
        VertexDistance(V vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        // Compare two VertexDistance objects based on distance for priority queue ordering
        @Override
        public int compareTo(VertexDistance<V> other) {
            return Double.compare(this.distance, other.distance); // Order by smallest distance first
        }
    }
}