package org.pg4200.sol08;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by arcuri82 on 03-Nov-17.
 */
public class AllPathsGraphTest {

    @Test
    public void test(){

        AllPathsGraph<String> graph = new AllPathsGraph<>();
        graph.addEdge("0","X");
        graph.addEdge("X","1");
        graph.addEdge("X","Y");
        graph.addEdge("1","2");
        graph.addEdge("2","Y");
        graph.addEdge("1","3");
        graph.addEdge("3","4");
        graph.addEdge("3","5");
        graph.addEdge("4","5");


        List<List<String>> paths = graph.findAllPaths("X","5");
        assertEquals(4, paths.size());
        assertTrue(paths.stream().anyMatch(p -> p.size() == 4));
        assertTrue(paths.stream().anyMatch(p -> p.size() == 5));
        assertTrue(paths.stream().anyMatch(p -> p.size() == 6));
        assertTrue(paths.stream().anyMatch(p -> p.size() == 7));
    }
}