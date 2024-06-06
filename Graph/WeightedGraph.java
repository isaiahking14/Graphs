package Graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import GraphNode.Edge;

public class WeightedGraph {
    private static int vertices;
    private static LinkedList<Edge>[] adjacencylist;

    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        adjacencylist = new LinkedList[vertices];
        // initialize adjacency lists for all the vertices
        for (int i = 0; i < vertices; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addEgde(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        adjacencylist[source].addFirst(edge); // for directed graph
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            LinkedList<Edge> list = adjacencylist[i];
            for (int j = 0; j < list.size(); j++) {
                System.out.println("vertex-" + i + " is connected to " +
                        list.get(j).destination + " with weight " + list.get(j).weight);
            }
        }
    }

    public void primMST() {
        boolean[] visited = new boolean[vertices];
        Edge[] result = new Edge[vertices];
        int[] distances = new int[vertices];
        
        // Initialize all keys to infinity and result to null
        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
            result[i] = null;
        }

        // Use a priority queue to store vertices that are being pre-included in MST
        PriorityQueue<Edge> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(o -> o.weight));

        // Start from the first vertex
        distances[0] = 0;
        pq.add(new Edge(-1, 0, 0));

        while (!pq.isEmpty()) {
            // Extract the vertex with minimum key value
            Edge edge = pq.poll();
            int u = edge.destination;

            // Include the extracted vertex to MST
            visited[u] = true;

            // Iterate over all adjacent vertices of u
            for (GraphNode.Edge e : adjacencylist[u]) {
                int v = e.destination;
                int weight = e.weight;

                // If v is not in MST and weight of (u,v) is smaller than current key of v
                if (!visited[v] && distances[v] > weight) {
                    distances[v] = weight;
                    pq.add(new Edge(u, v, weight));
                    result[v] = e;
                }
            }
        }

        // Print the result
        printMST(result);
    }

    public void printMST(Edge[] result) {
        int totalWeight = 0;
        for (int i = 1; i < vertices; i++) {
            if (result[i] != null) {
                System.out.println("Edge: " + result[i].source + " - " + result[i].destination + " weight: " + result[i].weight);
                totalWeight += result[i].weight;
            }
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    public void dijkstra(int startVertex) {
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];
        PriorityQueue<GraphNode.Edge> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(o -> o.weight));

        for (int i = 0; i < vertices; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[startVertex] = 0;

        pq.add(new GraphNode.Edge(startVertex, startVertex, 0));

        while (!pq.isEmpty()) {
            GraphNode.Edge edge = pq.poll();
            int u = edge.destination;

            if (!visited[u]) {
                visited[u] = true;

                LinkedList<GraphNode.Edge> list = adjacencylist[u];
                for (int i = 0; i < list.size(); i++) {
                    GraphNode.Edge adjEdge = list.get(i);
                    int v = adjEdge.destination;
                    int weight = adjEdge.weight;

                    if (!visited[v] && distances[u] != Integer.MAX_VALUE && (distances[u] + weight < distances[v])) {
                        distances[v] = distances[u] + weight;
                        pq.add(new GraphNode.Edge(u, v, distances[v]));
                    }
                }
            }
        }
        printDijkstra(distances, startVertex);
    }

    public void printDijkstra(int[] distances, int startVertex) {
        System.out.println("Dijkstra Algorithm: (Source: " + startVertex + ")");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Vertex: " + i + " Distance: " + distances[i]);
        }
    }

}