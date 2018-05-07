package org.pg4200.les08;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * A graph is composed of vertices and edges.
 * <p>
 * A vertex can be considered as a node.
 * <p>
 * An edge connect a vertex X to another vertex Y.
 * Note, if Y==X, then it is a self-connection.
 * <p>
 * Created by arcuri82 on 25-Oct-17.
 */
public interface Graph<V> {

    void addVertex(V vertex);

    /**
     * Create edge from "from" to "to".
     * If either of them does not exist, create
     * those vertices.
     */
    void addEdge(V from, V to);

    int getNumberOfVertices();

    int getNumberOfEdges();

    void removeEdge(V from, V to);

    /**
     * Besides removing the vertex, also remove
     * all edges in which it is involved.
     */
    void removeVertex(V vertex);

    /**
     * Given a vertex X, return a collection of all
     * other vertices that can be reached from X.
     */
    Collection<V> getAdjacents(V vertex);

    /**
     * Use Depth First Search (DFS) to find a path (if any)
     * from a "start" vertex to a "end" vertex.
     */
    List<V> findPathDFS(V start, V end);

    /**
     * Use Breadth First Search (BFS) to find path
     */
    List<V> findPathBFS(V start, V end);

    default boolean hasPath(V start, V end) {
        return findPathDFS(start, end) != null;
    }

    /**
     *  Given a vertex X, return all vertices that have a path reaching them from X.
     */
    Set<V> findConnected(V vertex);

    /**
     *  Given a collection of vertices, return all vertices that have a path reaching
     *  them from any vertex in the input collection
     */
    Set<V> findConnected(Iterable<V> vertices);
}
