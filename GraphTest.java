import java.util.LinkedList;
import java.util.Queue;

import Graph.Graph;
import GraphNode.Node;

public class GraphTest {
    
    public static void main(String[] args) {

        Graph<Node> graph = new Graph<Node>();
        Node isaiah = new Node("isaiah");
        Node epic = new Node("epic");
        Node hehe = new Node("hehe");

        graph.addVertex(isaiah);
        graph.addVertex(epic);
        graph.addVertex(hehe);
        graph.addEdge(hehe, epic);
        graph.addEdge(hehe, isaiah);
        System.out.println(graph.breadthFirstSearch(isaiah));
    }
}
