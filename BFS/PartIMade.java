package BFS;

import java.util.*;

import Graph.*;
import GraphNode.Node;

public class PartIMade<T>{
    final private HashMap<T, Set<T>> adjacencyList;

    public PartIMade(Graph g) {
        this.adjacencyList = new HashMap<>();
    }
    /*
     * breadth first search useful to find the shortest path to a give node on an
     * unweighted graph.
     * explores nodes and edges of graph
     */
    public Set<T> breadthFirstSearch(T start) {
        if (!this.adjacencyList.containsKey(start)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        
        queue.add(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (T neighbor : this.getNeighbors(vertex)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        
        
        return visited;
    }

    public Set<T> depthFirstSearch(T start) {
        if (!this.adjacencyList.containsKey(start)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        
        stack.push(start);
        
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                
                for (T neighbor : this.getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        
        return visited;
    }


}