package org.pg4200.graph;

import java.util.Objects;
import java.util.Set;

/**
 * Created by arcuri82 on 26-Oct-17.
 */
public class DirectedGraph<V> extends UndirectedGraph<V> {

    @Override
    public void addEdge(V from, V to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        addVertex(from);
        addVertex(to);

        graph.get(from).add(to);
    }

    @Override
    public int getNumberOfEdges() {
        return graph.values().stream()
                .mapToInt(s -> s.size())
                .sum();
    }


    @Override
    public void removeEdge(V from, V to) {

        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        Set<V> connectedFrom = graph.get(from);

        if(connectedFrom != null){
            connectedFrom.remove(to);
        }
    }

    @Override
    public void removeVertex(V vertex) {

        Objects.requireNonNull(vertex);

        if(! graph.containsKey(vertex)){
            //nothing to do
            return;
        }

        graph.values()
                .forEach(c -> c.remove(vertex));

        graph.remove(vertex);
    }

}
