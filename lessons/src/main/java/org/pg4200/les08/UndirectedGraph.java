package org.pg4200.les08;

// WARNING: this is one of the 12 classes you need to study and know by heart


import java.util.*;

/**
 * Undirected means: every time there is a connection
 * (ie an edge) from X to Y, then there is also a connection
 * from Y to X.
 *
 * Created by arcuri82 on 25-Oct-17.
 */
public class UndirectedGraph<V> implements  Graph<V>{

    /**
     * Key -> a vertex in the graph
     * Value -> set of all vertices that connect to the Key, ie
     *          the Key is the "from"/"source"
     */
    protected Map<V, Set<V>> graph = new HashMap<>();


    @Override
    public void addVertex(V vertex) {
        Objects.requireNonNull(vertex);

        graph.putIfAbsent(vertex, new HashSet<>());
    }

    @Override
    public void addEdge(V from, V to) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        addVertex(from);
        addVertex(to);

        graph.get(from).add(to);

        if(! from.equals(to)) {
            //ie, if not a self-loop
            graph.get(to).add(from);
        }
    }

    @Override
    public int getNumberOfVertices() {
        return graph.size();
    }

    @Override
    public int getNumberOfEdges() {
        long edges =  graph.values().stream()
                .mapToInt(s -> s.size())
                .sum();

        /*
            As each edge is represented by 2 connections,
            we need to divide by 2.
            Ie, if edge X-Y, we have 1 connection from
            X to Y, and 1 from Y to X.

            However, we also have to consider self-loops X-X
         */

        edges += graph.entrySet().stream()
                .filter(e -> e.getValue().contains(e.getKey()))
                .count();

        return (int) edges / 2;
    }

    @Override
    public void removeEdge(V from, V to) {

        Objects.requireNonNull(from);
        Objects.requireNonNull(to);

        Set<V> connectedFrom = graph.get(from);
        Set<V> connectedTo = graph.get(to);

        if(connectedFrom != null){
            connectedFrom.remove(to);
        }
        if(connectedTo != null){
            connectedTo.remove(from);
        }
    }

    @Override
    public void removeVertex(V vertex) {

        Objects.requireNonNull(vertex);

        if(! graph.containsKey(vertex)){
            //nothing to do
            return;
        }

        /*
            Before we can remove the vertex, we have to
            remove all other connections to such vertex in the
            other vertices.
            Note: we can call "forEach" directly on a collection
            without opening a stream() first.
         */
        graph.get(vertex).forEach(v -> graph.get(v).remove(vertex));

        graph.remove(vertex);
    }

    @Override
    public Collection<V> getAdjacents(V vertex) {

        Objects.requireNonNull(vertex);

        return graph.get(vertex);
    }

    @Override
    public List<V> findPathDFS(V start, V end) {

        if(! graph.containsKey(start) || ! graph.containsKey(end)){
            /*
                no point in searching if either start or end are not
                in the graph.
             */
            return null;
        }

        if(start.equals(end)){
            //we do not consider cycles
            throw new IllegalArgumentException();
        }

        Set<V> alreadyVisited = new HashSet<>();

        /*
            Java API has a Stack implementation, but it should not
            be used, as based on deprecated Vector class.
            To represent a stack, we need to use Deque (double ended queue).
            See the JavaDocs, eg
            https://docs.oracle.com/javase/9/docs/api/java/util/Deque.html

            push(e)	-> addFirst(e)
            pop()	-> removeFirst()
            peek()	-> peekFirst()
         */
        Deque<V> stack = new ArrayDeque<>();

        dfs(alreadyVisited, stack, start, end);

        if(isPathTo(stack, end)){
            List<V> path = new ArrayList<>(stack);
            Collections.reverse(path);
            return path;
        }

        return null;
    }

    private void dfs(Set<V> alreadyVisited, Deque<V> stack, V current, V end){

        alreadyVisited.add(current);
        stack.addFirst(current);

        if(isPathTo(stack, end)){
            return;
        }

        for(V connected : getAdjacents(current)){
            if(alreadyVisited.contains(connected)){
                /*
                    If we have already analysed a vertex,
                    no point in re-analyzing it again.
                 */
                continue;
            }

            dfs(alreadyVisited, stack, connected, end);

            if(! isPathTo(stack, end)){
                //backtrack
                stack.removeFirst();
            } else {
                return;
            }
        }
    }

    protected boolean isPathTo(Deque<V> stack, V vertex){
        return !stack.isEmpty() && stack.peekFirst().equals(vertex);
    }

    @Override
    public List<V> findPathBFS(V start, V end) {

        // same initial checks as in DFS

        if(! graph.containsKey(start) || ! graph.containsKey(end)){
            return null;
        }

        if(start.equals(end)){
            throw new IllegalArgumentException();
        }


        Queue<V> queue = new ArrayDeque<>();
        Map<V,V> bestParent = new HashMap<>();

        queue.add(start);

        mainLoop: while(! queue.isEmpty()){

            V parent = queue.poll();

            for(V child : graph.get(parent)){

                if( child.equals(start) || bestParent.get(child) != null){
                    continue;
                }
                bestParent.put(child, parent);

                if(child.equals(end)){
                    /*
                        found a path, no need to analyze
                        the rest of the queue
                     */
                    break mainLoop;
                }

                queue.add(child);
            }
        }

        if(! bestParent.containsKey(end)){
            return null;
        }

        /*
            At this point, we know that there is a path.
            So, starting from "end", we need to use the
            bestParent map to backtrack the path from "end"
            to "start"
         */

        List<V> path = new ArrayList<>();
        V current = end;
        while (current != null){
            path.add(0, current);
            current = bestParent.get(current);
        }

        return path;
    }

    @Override
    public Set<V> findConnected(V vertex) {

        if(! graph.containsKey(vertex)){
            return null;
        }

        Set<V> connected = new HashSet<>();
        findConnected(connected, vertex);

        return connected;
    }

    @Override
    public Set<V> findConnected(Iterable<V> vertices) {

        Set<V> connected = new HashSet<>();
        if(vertices==null){
            return connected;
        }

        for(V vertex : vertices) {
            if(! graph.containsKey(vertex)){
                continue;
            }
            findConnected(connected, vertex);
        }

        return connected;
    }

    private void findConnected(Set<V> connected, V vertex){
        if(connected.contains(vertex)){
            return;
        }

        connected.add(vertex);

        /*
            Look and add all connected vertices.
            Then, reapply this code recursively on
            all these connected vertices.
         */

        graph.get(vertex).stream()
                .filter(c -> ! connected.contains(c))
                .forEach(c -> findConnected(connected, c));
    }

}
