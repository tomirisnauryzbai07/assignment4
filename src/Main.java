public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 5);
        graph.addEdge("B", "D", 10);
        graph.addEdge("C", "E", 3);
        graph.addEdge("E", "D", 4);
        graph.addEdge("D", "F", 11);

        System.out.println("BFS Path from A to D:");
        Search<String> bfs = new BFS<>(graph, "A");
        for (Vertex<String> v : bfs.getPath("D")) {
            System.out.print(v + " ");
        }

        System.out.println("\n\nDijkstra's Path from A to D:");
        Search<String> dijkstra = new DijkstraSearch<>(graph, "A");
        for (Vertex<String> v : dijkstra.getPath("D")) {
            System.out.print(v + " ");
        }
    }
}