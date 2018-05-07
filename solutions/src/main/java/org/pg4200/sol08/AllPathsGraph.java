package org.pg4200.sol08;

import org.pg4200.les08.UndirectedGraph;

import java.util.*;

/**
 * Created by arcuri82 on 03-Nov-17.
 */
public class AllPathsGraph<V> extends UndirectedGraph<V> {


    public List<List<V>> findAllPaths(V start, V end) {

        if (!graph.containsKey(start) && !graph.containsKey(end)) {
            return Collections.emptyList();
        }

        if (start.equals(end)) {
            //we do not consider cycles
            throw new IllegalArgumentException();
        }

        Deque<V> stack = new ArrayDeque<>();

        List<List<V>> paths = new ArrayList<>();

        dfs(paths, stack, start, end);

        return paths;
    }

    private void dfs(List<List<V>> paths, Deque<V> stack, V current, V end) {

        stack.addFirst(current);

        if (isPathTo(stack, end)) {
            List<V> path = new ArrayList<>(stack);
            Collections.reverse(path);
            paths.add(path);
            return;
        }

        for (V connected : getAdjacents(current)) {
            if (stack.contains(connected)) {
                continue;
            }

            dfs(paths, stack, connected, end);
            //backtrack
            stack.removeFirst();
        }
    }
}
