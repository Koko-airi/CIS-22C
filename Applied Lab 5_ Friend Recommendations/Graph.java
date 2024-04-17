/**
 * Graph.java
 *
 * @author Michael Chen
 * CIS 22C, Lab 15
 */

import java.util.ArrayList;

public class Graph {
    private int vertices;
    private int edges;
    private ArrayList<LinkedList<Integer>> adj;
    private ArrayList<Character> color;
    private ArrayList<Integer> distance;
    private ArrayList<Integer> parent;
    private ArrayList<Integer> discoverTime;
    private ArrayList<Integer> finishTime;
    private static int time = 0;

    /** Constructors and Destructors */

    /**
     * initializes an empty graph, with n vertices and 0 edges
     *
     * @param numVtx the number of vertices in the graph
     * @throws IllegalArgumentException when numVtx <= 0
     * @precondition numVtx > 0
     */
    public Graph(int numVtx) throws IllegalArgumentException {
        if (numVtx <= 0)
            throw new IllegalArgumentException();
        vertices = numVtx;
        edges = 0;
        adj = new ArrayList<>(numVtx + 1);
        color = new ArrayList<>(numVtx);
        distance = new ArrayList<>(numVtx);
        parent = new ArrayList<>(numVtx);
        discoverTime = new ArrayList<>(numVtx);
        finishTime = new ArrayList<>(numVtx);

        for (int i = 0; i < numVtx + 1; i++) {
            adj.add(new LinkedList<>());
            color.add('W');
            distance.add(-1);
            parent.add(0);
            discoverTime.add(-1);
            finishTime.add(-1);
        }
    }

    /*** Accessors ***/

    /**
     * Returns the number of edges in the graph
     *
     * @return the number of edges
     */
    public int getNumEdges() {
        return edges;
    }

    /**
     * Returns the number of vertices in the graph
     *
     * @return the number of vertices
     */
    public int getNumVertices() {
        return vertices;
    }

    /**
     * returns whether the graph is empty (no edges)
     *
     * @return whether the graph is empty
     */
    public boolean isEmpty() {
        return edges == 0;
    }

    /**
     * Returns the value of the distance[v]
     *
     * @param v a vertex in the graph
     * @return the distance of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     * @precondition 0 < v <= vertices
     */
    public Integer getDistance(Integer v) throws IndexOutOfBoundsException {
        if (v <= 0 || v > getNumVertices())
            throw new IndexOutOfBoundsException();
        return distance.get(v);
    }

    /**
     * Returns the value of the parent[v]
     *
     * @param v a vertex in the graph
     * @return the parent of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     * @precondition 0 < v <= vertices
     */
    public Integer getParent(Integer v) throws IndexOutOfBoundsException {
        if (v <= 0 || v > getNumVertices())
            throw new IndexOutOfBoundsException();
        return parent.get(v);
    }

    /**
     * Returns the value of the color[v]
     *
     * @param v a vertex in the graph
     * @return the color of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     * @precondition 0 < v <= vertices
     */
    public Character getColor(Integer v) throws IndexOutOfBoundsException {
        if (v <= 0 || v > getNumVertices())
            throw new IndexOutOfBoundsException();
        return color.get(v);
    }

    /**
     * Returns the value of the discoverTime[v]
     *
     * @param v a vertex in the graph
     * @return the discover time of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     * @precondition 0 < v <= vertices
     */
    public Integer getDiscoverTime(Integer v) throws IndexOutOfBoundsException {
        if (v <= 0 || v > getNumVertices())
            throw new IndexOutOfBoundsException();
        return discoverTime.get(v);
    }

    /**
     * Returns the value of the finishTime[v]
     *
     * @param v a vertex in the graph
     * @return the finish time of vertex v
     * @throws IndexOutOfBoundsException when v is out of bounds
     * @precondition 0 < v <= vertices
     */
    public Integer getFinishTime(Integer v) throws IndexOutOfBoundsException {
        if (v <= 0 || v > getNumVertices())
            throw new IndexOutOfBoundsException();
        return finishTime.get(v);
    }

    /**
     * Returns the LinkedList stored at index v
     *
     * @param v a vertex in the graph
     * @return the adjacency LinkedList at v
     * @throws IndexOutOfBoundsException when v is out of bounds
     * @precondition 0 < v <= vertices
     */
    public LinkedList<Integer> getAdjacencyList(Integer v)
            throws IndexOutOfBoundsException {
        if (v <= 0 || v > getNumVertices())
            throw new IndexOutOfBoundsException();
        return adj.get(v);
    }

    /*** Manipulation Procedures ***/

    /**
     * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into
     * the list at index u) @precondition, 0 < u, v <= vertices
     *
     * @param u a vertex in the graph
     * @param v a vertex in the graph
     * @throws IndexOutOfBoundsException when u or v is out of bounds
     */
    public void addDirectedEdge(Integer u, Integer v)
            throws IndexOutOfBoundsException {
        if (v <= 0 || v > getNumVertices() || u <= 0 || u > getNumVertices())
            throw new IndexOutOfBoundsException();

        adj.get(u).addLast(v);
        edges++;
    }

    /**
     * Inserts vertex v into the adjacency list of vertex u (i.e. inserts v into
     * the list at index u) and inserts u into the adjacent vertex list of v.
     *
     * @param u a vertex in the graph
     * @param v a vertex in the graph
     * @throws IndexOutOfBoundsException when u or v is out of bounds
     * @precondition, 0 < u, v <= vertices
     */
    public void addUndirectedEdge(Integer u, Integer v)
            throws IndexOutOfBoundsException {
        if (v <= 0 || v > getNumVertices() || u <= 0 || u > getNumVertices())
            throw new IndexOutOfBoundsException();


        adj.get(u).addLast(v);
        adj.get(v).addLast(u);
        edges++;

    }

    /*** Additional Operations ***/

    /**
     * Creates a String representation of the Graph Prints the adjacency list of
     * each vertex in the graph, vertex: <space separated list of adjacent
     * vertices>
     *
     * @return a space separated list of adjacent vertices
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < adj.size(); i++) {
            sb.append(i + ": " + adj.get(i));
        }

        return sb.toString();
    }

    /**
     * Performs breath first search on this Graph give a source vertex
     *
     * @param source the starting vertex
     * @throws IndexOutOfBoundsException when the source vertex is out of bounds
     *                                   of the graph
     * @precondition source is a vertex in the graph
     */
    public void BFS(Integer source) throws IndexOutOfBoundsException {
        if (source <= 0 || source > getNumVertices())
            throw new IndexOutOfBoundsException();

        distance.set(source, 0);
        boolean exist = true;
        time = 0;
        while (exist) {
            exist = false;

            for (int i = 1; i < vertices; i++) {
                if (distance.get(i) == time) {
                    LinkedList<Integer> temp = adj.get(i);
                    color.set(i, 'B');

//                    System.out.println(i + "   " + temp);
                    temp.positionIterator();
                    while (!temp.offEnd()) {
                        int next = temp.getIterator();

                        if (distance.get(next) == -1) {
                            parent.set(next, i);
                            distance.set(next, time + 1);
                            exist = true;
                            color.set(next, 'B');
                        }
                        temp.advanceIterator();
                    }
                }
            }
            time++;
        }
        time = 0;
    }

    /**
     * Performs depth first search on this Graph in order of vertex lists
     */
    public void DFS() {
        time = 0;
        for (int i = 1; i < vertices; i++) {
            if (color.get(i) == 'W') {
                visit(i);
            }
        }
    }

    /**
     * Private recursive helper method for DFS
     *
     * @param vertex the vertex to visit
     */
    private void visit(int vertex) {
        color.set(vertex, 'G');
        discoverTime.set(vertex, ++time);
        adj.get(vertex).positionIterator();
        for (int i = 0; i < adj.get(vertex).getLength(); i++) {
            int temp = adj.get(vertex).getIterator();
            if (color.get(temp) == 'W') {
                parent.set(temp, vertex);
                visit(temp);
            }
            adj.get(vertex).advanceIterator();
        }
        color.set(vertex, 'B');

        finishTime.set(vertex, ++time);
    }

//    public String printDis()
//    {
//        String ans = "";
//        for(int i = 1; i < getNumVertices(); i++)
//            ans += i + ": " + distance.get(i) + "\n";
//
//        return ans;
//    }
}
