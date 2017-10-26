package org.pg4200.graph;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by arcuri82 on 25-Oct-17.
 */
public interface Graph<V> {

    void addVertex(V vertex);

    void addEdge(V from, V to);

    int getNumberOfVertices();

    int getNumberOfEdges();

    void removeEdge(V from, V to);

    void removeVertex(V vertex);

    Collection<V> getAdjacents(V vertex);

    List<V> findPathDFS(V start, V end);

    List<V> findPathBFS(V start, V end);

    default boolean hasPath(V start, V end){
        return findPathDFS(start, end) != null;
    }

    Set<V> findConnected(V vertex);
}
