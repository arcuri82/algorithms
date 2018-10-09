package org.pg4200.les08;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Created by arcuri82 on 26-Oct-17.
 */
public class DirectedGraphTest {


     /*
        1 == 2 -> 3=        9
             ^    ||        ||
             |    ||        ||
             4    ||        10
             ^    ||
             |    ||
            =5 <- 6
             ||   ^
             ||   |
             7 -> 8

     */

    private Graph<Integer> createGraph() {

        Graph<Integer> graph = new DirectedGraph<>();

        graph.addEdge(1, 2);
        graph.addEdge(2, 1);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        graph.addEdge(3, 6);
        graph.addEdge(6, 3);
        graph.addEdge(6, 5);
        graph.addEdge(5, 5);
        graph.addEdge(5, 4);
        graph.addEdge(4, 2);
        graph.addEdge(5, 7);
        graph.addEdge(7, 5);
        graph.addEdge(7, 8);
        graph.addEdge(8, 6);
        graph.addEdge(9, 10);
        graph.addEdge(10, 9);

        assertEquals(10, graph.getNumberOfVertices());
        assertEquals(16, graph.getNumberOfEdges());

        return graph;
    }


    @Test
    public void testShortestPath(){

        Graph<Integer> graph = createGraph();

        List<Integer> path = graph.findPathBFS(5, 6);

        assertNotNull(path);
        assertEquals(4, path.size());
        assertEquals(5, (int) path.get(0));
        assertEquals(7, (int) path.get(1));
        assertEquals(8, (int) path.get(2));
        assertEquals(6, (int) path.get(3));

        //note: a longer path would be 5,4,2,3,6
    }

    @Test
    public void testDFS(){

        Graph<Integer> graph = createGraph();

        List<Integer> path = graph.findPathDFS(1, 8);

        verifyPath_1_8(path);
    }

    @Test
    public void testBFS(){

        Graph<Integer> graph = createGraph();

        List<Integer> path = graph.findPathBFS(1, 8);

        verifyPath_1_8(path);
    }

    private void verifyPath_1_8(List<Integer> path){
        //only 1 path exists
        assertNotNull(path);
        assertEquals(7, path.size());
        assertEquals(1, (int) path.get(0));
        assertEquals(2, (int) path.get(1));
        assertEquals(3, (int) path.get(2));
        assertEquals(6, (int) path.get(3));
        assertEquals(5, (int) path.get(4));
        assertEquals(7, (int) path.get(5));
        assertEquals(8, (int) path.get(6));
    }
}