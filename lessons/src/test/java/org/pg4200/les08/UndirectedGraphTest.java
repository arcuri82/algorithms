package org.pg4200.les08;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by arcuri82 on 26-Oct-17.
 */
public class UndirectedGraphTest {

    /*
        1 -- 2 -- 3-        9
             |    |         |
             |    |         |
             4    |        10
             |    |
             |    |
            -5 -- 6
             |    |
             |    |
             7 -- 8

     */

    private Graph<Integer> createGraph() {

        Graph<Integer> graph = new UndirectedGraph<>();

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        graph.addEdge(2, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 5);
        graph.addEdge(5, 6);
        graph.addEdge(3, 6);
        graph.addEdge(5, 7);
        graph.addEdge(7, 8);
        graph.addEdge(6, 8);
        graph.addEdge(9, 10);

        assertEquals(10, graph.getNumberOfVertices());
        assertEquals(12, graph.getNumberOfEdges());

        return graph;
    }

    @Test
    public void testShortestPath_simple() {

        Graph<Integer> graph = createGraph();

        List<Integer> path = graph.findPathBFS(1, 2);

        assertNotNull(path);
        assertEquals(2, path.size());
        assertEquals(1, (int) path.get(0));
        assertEquals(2, (int) path.get(1));
    }

    @Test
    public void testShortestPath_complex() {

        Graph<Integer> graph = createGraph();

        List<Integer> path = graph.findPathBFS(2, 8);

        assertNotNull(path);
        assertEquals(4, path.size());
        assertEquals(2, (int) path.get(0));
        assertEquals(3, (int) path.get(1));
        assertEquals(6, (int) path.get(2));
        assertEquals(8, (int) path.get(3));
    }

    private <T> void assertNoLoop(List<T> path) {
        Set<T> set = new HashSet<>(path);
        assertTrue(path.size() > 1);
        assertEquals(path.size(), set.size());
    }


    @Test
    public void testBFSvsDFS() {

        Graph<Integer> graph = createGraph();

        for (int start = 1; start <= 8; start++) {
            for (int end = 1; end <= 8; end++) {

                if (start == end) {
                    continue;
                }

                List<Integer> bfs = graph.findPathBFS(start, end);
                List<Integer> dfs = graph.findPathDFS(start, end);

                System.out.println("Checking: <" + start + ", " + end + ">");

                assertNotNull(bfs);
                assertNotNull(dfs);

                assertNoLoop(bfs);
                assertNoLoop(dfs);

                assertTrue(bfs.size() <= dfs.size());
            }
        }
    }

    @Test
    public void testNoPathBFS(){
        Graph<Integer> graph = createGraph();
        List<Integer> path = graph.findPathBFS(3, 9);
        assertNull(path);
    }

    @Test
    public void testNoPathDFS(){
        Graph<Integer> graph = createGraph();
        List<Integer> path = graph.findPathDFS(10, 4);
        assertNull(path);
    }

    @Test
    public void testConnected(){

        Graph<Integer> graph = createGraph();
        Set<Integer> left = graph.findConnected(3);
        Set<Integer> right = graph.findConnected(10);

        assertEquals(8, left.size());
        assertEquals(2, right.size());
    }


    @Test
    public void testDelete_shortestPath(){

        Graph<Integer> graph = createGraph();

        List<Integer> path = graph.findPathBFS(1, 8);
        assertNotNull(path);
        //1,2,3,6,8
        assertEquals(5, path.size());

        graph.removeEdge(2,3);

        path = graph.findPathBFS(1, 8);
        assertNotNull(path);
        //1,2,4,5,7,8
        assertEquals(6, path.size());
    }

    @Test
    public void testDelete_disconnect(){

        Graph<Integer> graph = createGraph();

        Set<Integer> set = graph.findConnected(3);
        assertEquals(8, set.size());

        graph.removeVertex(4);
        set = graph.findConnected(3);
        assertEquals(7, set.size());

        graph.removeVertex(6);
        set = graph.findConnected(3);
        assertEquals(3, set.size());
    }
}