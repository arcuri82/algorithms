package org.pg4200.ex09;

import org.pg4200.les09.UndirectedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class AllPathsGraph<V> extends UndirectedGraph<V> {

    public List<List<V>> findAllPaths(V start, V end){

        if(!graph.containsKey(start) || !graph.containsKey(end)){
            return Collections.emptyList();
        }

        if(start.equals(end)){
            throw new IllegalArgumentException();
        }

        Stack<V> stack = new Stack<V>();
        List<List<V>> paths = new ArrayList<>();

        findAllPathsDF(stack, paths, start, end);

        return paths;
    }

    private void findAllPathsDF(Stack<V> stack,
                                List<List<V>> paths,
                                V currentVertex,
                                V end){

        stack.push(currentVertex);

        if (currentVertex.equals(end)){
            List<V> path = new ArrayList<>(stack);
            paths.add(path);
            stack.pop();
            return;
        }

        List<V> connected = graph.get(currentVertex).stream()
                .filter(v -> !stack.contains(v)).collect(Collectors.toList());

        while(!connected.isEmpty()){
            V nextVertex = connected.iterator().next();
            connected.remove(nextVertex);
            findAllPathsDF(stack, paths, nextVertex, end);
        }

        stack.pop();
    }


    public List<V> shortestPath(V start, V end){

        Queue<V> queue = new ArrayDeque<>();
        Map<V, V> map = new HashMap<>();

        if(!graph.containsKey(start) || !graph.containsKey(end)) return Collections.emptyList();

        if(start.equals(end)){
            throw new IllegalArgumentException("Tsk Tsk");
        }

        map.put(start, null);

        queue.addAll(graph.get(start));

        List<V> path = findShortestPathBF(queue, map, end);

        return null;
    }

    private List<V> findShortestPathBF(Queue<V> queue,
                                Map<V, V> map,
                                V end) {


        while(!queue.isEmpty() && !map.containsKey(end)){
            V currentVertex = queue.remove();

            Set<V> connections = graph.get(currentVertex).stream()
                    .filter(v -> !queue.contains(v))
                    .filter(v -> !map.containsKey(v))
                    .collect(Collectors.toSet());

            for(V vertex : connections){
                map.put(vertex, currentVertex);
            }
        }

        List<V> path = new ArrayList<>();
        if(map.containsKey(end)){

            V currentNode = end;
            while(map.get(currentNode)!= null){
                path.add(currentNode);
                currentNode = map.get(currentNode);
            }

        }
        return path;
    }



}
