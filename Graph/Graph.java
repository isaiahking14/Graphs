package Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * A simple undirected and unweighted graph implementation.
 * 
 * @param <T> The type that would be used as vertex.
 */
public class Graph<T> {
    final private HashMap<T, Set<T>> adjacencyList;
    
    /**
     * Create new Graph object.
     */
    public Graph() {
        this.adjacencyList = new HashMap<>();
    }
    
    /**
     * Add new vertex to the graph.
     * 
     * @param v The vertex object. 
     */
    public void addVertex(T v) {
        if (this.adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException("Vertex already exists.");
        }
        this.adjacencyList.put(v, new HashSet<T>());
    }
    
    /**
     * Remove the vertex v from the graph.
     * 
     * @param v The vertex that will be removed.
     */
    public void removeVertex(T v) {
        if (!this.adjacencyList.containsKey(v)) {
            throw new IllegalArgumentException("Vertex doesn't exist.");
        }
        
        this.adjacencyList.remove(v);
        
        for (T u: this.getAllVertices()) {
            this.adjacencyList.get(u).remove(v);
        }
    }
    
    /**
     * Add new edge between vertex. Adding new edge from u to v will
     * automatically add new edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
    public void addEdge(T v, T u) {
        if (!this.adjacencyList.containsKey(v) || !this.adjacencyList.containsKey(u)) {
            throw new IllegalArgumentException();
        }
        
        this.adjacencyList.get(v).add(u);
        this.adjacencyList.get(u).add(v);
    }
    
    /**
     * Remove the edge between vertex. Removing the edge from u to v will 
     * automatically remove the edge from v to u since the graph is undirected.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     */
    public void removeEdge(T v, T u) {
        if (!this.adjacencyList.containsKey(v) || !this.adjacencyList.containsKey(u)) {
            throw new IllegalArgumentException();
        }
        
        this.adjacencyList.get(v).remove(u);
        this.adjacencyList.get(u).remove(v);
    }
    
    /**
     * Check adjacency between 2 vertices in the graph.
     * 
     * @param v Start vertex.
     * @param u Destination vertex.
     * @return <tt>true</tt> if the vertex v and u are connected.
     */
    public boolean isAdjacent(T v, T u) {
        return this.adjacencyList.get(v).contains(u);
    }
    
    /**
     * Get connected vertices of a vertex.
     * 
     * @param v The vertex.
     * @return An iterable for connected vertices.
     */
    public Iterable<T> getNeighbors(T v) {
        return this.adjacencyList.get(v);
    }
    
    /**
     * Get all vertices in the graph.
     * 
     * @return An Iterable for all vertices in the graph.
     */
    public Iterable<T> getAllVertices() {
        return this.adjacencyList.keySet();
    }

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