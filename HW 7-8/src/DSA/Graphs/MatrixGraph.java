package DSA.Graphs;

import java.util.Collection;
import DSA.Graphs.GCA.AdjacencyVect;

/**
 * MatrixGraph class implements the GTUGraph interface representing
 * an undirected graph using an adjacency vector array.
 * 
 * Each vertex has an AdjacencyVect storing its neighbors efficiently.
 * 
 * Time Complexity Notes:
 * - setEdge, getEdge: O(1)
 * - getNeighbors: O(1)
 * - reset: O(n^2) due to re-initializing adjacency vectors of size n
 * - size: O(1)
 */
public class MatrixGraph implements GTUGraph {

    private AdjacencyVect[] vect;  // Array of adjacency vectors, one per vertex
    private int size;              // Number of vertices in the graph

    /**
     * Constructs a MatrixGraph with given number of vertices.
     * Initializes each vertex adjacency vector with capacity = size.
     * Time Complexity: O(n^2) because each AdjacencyVect has size n
     * @param size number of vertices
     */
    public MatrixGraph(int size) {
        this.size = size;
        this.vect = new AdjacencyVect[size];
        for (int i = 0; i < size; i++) {
            vect[i] = new AdjacencyVect(size);
        }
    }

    /**
     * Default constructor initializes an empty graph with zero vertices.
     */
    public MatrixGraph() {
        this.size = 0;
        this.vect = new AdjacencyVect[0];
    }

    /**
     * Adds an undirected edge between vertices v1 and v2.
     * Adds v2 to v1's adjacency vector and v1 to v2's adjacency vector.
     * Time Complexity: O(1)
     * 
     * @param v1 first vertex index
     * @param v2 second vertex index
     * @return true if either adjacency vector changed (edge added), false otherwise or invalid indices
     */
    @Override
    public Boolean setEdge(int v1, int v2) {
        if (v1 >= size || v2 >= size || v1 < 0 || v2 < 0) {
            return false;
        }
        boolean add1 = vect[v1].add(v2);
        boolean add2 = vect[v2].add(v1);
        return add1 || add2;
    }

    /**
     * Checks if an edge exists between vertices v1 and v2.
     * Time Complexity: O(1)
     * 
     * @param v1 first vertex index
     * @param v2 second vertex index
     * @return true if edge exists, false if no edge or invalid indices
     */
    @Override
    public Boolean getEdge(int v1, int v2) {
        if (v1 >= size || v2 >= size || v1 < 0 || v2 < 0) {
            return false;
        }
        return vect[v1].contains(v2);
    }

    /**
     * Returns a Collection of neighbors of vertex v.
     * Time Complexity: O(1)
     * 
     * @param v vertex index
     * @return Collection<Integer> of neighbors, or null if invalid index
     */
    @Override
    public Collection<Integer> getNeighbors(int v) {
        if (v >= size || v < 0) {
            return null;
        }
        return vect[v];
    }

    /**
     * Returns the number of vertices in the graph.
     * Time Complexity: O(1)
     * 
     * @return number of vertices
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Resets the graph with new size and reinitializes adjacency vectors.
     * Previous graph data is discarded.
     * Time Complexity: O(n^2) due to reinitialization of each adjacency vector of size n
     * 
     * @param size new number of vertices
     */
    @Override
    public void reset(int size) {
        this.size = size;
        vect = new AdjacencyVect[size];
        for (int i = 0; i < size; i++) {
            vect[i] = new AdjacencyVect(size);
        }
    }
}
