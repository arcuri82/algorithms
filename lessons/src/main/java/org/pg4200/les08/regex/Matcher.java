package org.pg4200.les08.regex;

import org.pg4200.les02.stack.MyStack;
import org.pg4200.les02.stack.MyStackArray;
import org.pg4200.les11.DirectedGraph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by arcuri82 on 07-May-18.
 */
public class Matcher {

    /**
     * The regular expression this matcher is built for
     */
    private final String regex;

    /**
     * Non-deterministic Finite State Machine, built from
     * the regular expression
     */
    private final DirectedGraph<Integer> nfsm;

    public Matcher(String regex) {
        this.regex = Objects.requireNonNull(regex);
        this.nfsm = buildNonDeterministicFiniteStateMachine();
    }

    protected DirectedGraph<Integer> buildNonDeterministicFiniteStateMachine(){

        MyStack<Integer> ops = new MyStackArray<>(regex.length());
        DirectedGraph<Integer> graph = new DirectedGraph<>();
        for (int i = 0; i < regex.length() + 1; i++) {
            graph.addVertex(i);
        }

        for (int i = 0; i < regex.length(); i++) {

            char current = regex.charAt(i);

            int lp = i;

            if (current == '(' || current == '|') {
                ops.push(i);

            } else if (current == ')') {
                int or = ops.pop();

                // 2-way or operator
                if (regex.charAt(or) == '|') {
                    lp = ops.pop();
                    graph.addEdge(lp, or+1);
                    graph.addEdge(or, i);
                } else if (regex.charAt(or) == '(') {
                    lp = or;
                } else {
                    //should never happen
                    assert false;
                }
            }

            // closure operator (uses 1-character lookahead)
            if (i < regex.length()-1 && regex.charAt(i+1) == '*') {
                graph.addEdge(lp, i+1);
                graph.addEdge(i+1, lp);
            }
            if (current == '(' || current == '*' || current == ')') {
                graph.addEdge(i, i + 1);
            }
        }

        if (ops.size() != 0) {
            throw new IllegalArgumentException("Invalid regular expression");
        }

        return graph;
    }

    public boolean match(String txt) {
        Objects.requireNonNull(txt);

        Set<Integer> vertices = nfsm.findConnected(0);

        for (int i = 0; i < txt.length(); i++) {

            char current = txt.charAt(i);

            if (current == '*' || current == '|' || current == '(' || current == ')') {
                throw new IllegalArgumentException("text contains the metacharacter '" + txt.charAt(i) + "'");
            }

            Set<Integer> match = new HashSet<>();
            for (int v : vertices) {
                if (v == regex.length()){
                    continue;
                }
                if ((regex.charAt(v) == txt.charAt(i)) || regex.charAt(v) == '.') {
                    match.add(v + 1);
                }
            }
            vertices = nfsm.findConnected(match);

            if (vertices.isEmpty()){
                return false;
            }
        }

        if(vertices.contains(regex.length())) {
            return true;
        }

        return false;
    }

}
