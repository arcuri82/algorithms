package org.pg4200.les09.regex;

import org.pg4200.les02.stack.MyStack;
import org.pg4200.les02.stack.MyStackArray;
import org.pg4200.les08.DirectedGraph;

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


    /**
     * Build a matcher for the given regular expression (regex).
     * Note: it supports only very basic form of regex.
     *
     * @param regex
     * @throws IllegalArgumentException if the regex is invalid
     */
    public Matcher(String regex) throws IllegalArgumentException {
        this.regex = Objects.requireNonNull(regex);
        this.nfsm = buildNonDeterministicFiniteStateMachine();
    }

    private DirectedGraph<Integer> buildNonDeterministicFiniteStateMachine() {

        /*
            To build the graph, we need a stack as a supporting data structure
            to handle parentheses () and the "or |" operator.
            We need to keep track of currently opened (.
            As we can have nesting, eg ((()())), we need to use a stack, as
            we can open a new ( before closing the previous ones, and we
            should not lose info on the those previous ones.

            Notice also how, to handle the algorithm of doing string regular
            expression matching, we deal with two data structures (graph and stack)
            that we have seen in previous classes, which might not be obvious to
            the user of this class, unless s/he look into the code.
         */
        MyStack<Integer> operations = new MyStackArray<>(regex.length());
        DirectedGraph<Integer> graph = new DirectedGraph<>();

        /*
            We need 1 vertex per element in the regex, plus a final
            acceptance state.
            Each vertex 'i' represents the element at position 'i' in the regex
         */
        for (int i = 0; i < regex.length() + 1; i++) {
            graph.addVertex(i);
        }


        /*
            here, we need add all the edges on the graph for the empty-transitions
            that can be taken without needing to consume data from the text
         */
        for (int i = 0; i < regex.length(); i++) {

            char current = regex.charAt(i);

            /*
                Either the index of current value in the regex, or
                the index of the closest opening '(' on the left when
                the current value is a closing ')'
             */
            int leftElement = i;

            // handle (, |, and ) for the stack
            if (current == '(' || current == '|') {
                //only two kinds of operations are pushed on stack
                operations.push(i);

            } else if (current == ')') {

                if(operations.isEmpty()){
                    throw new IllegalArgumentException("Invalid regular expression: closing not-opened parenthesis");
                }

                int op = operations.pop();

                // 2-way or operator
                if (regex.charAt(op) == '|') {

                    if(operations.isEmpty()){
                        throw new IllegalArgumentException("Invalid regular expression: closing not-opened parenthesis");
                    }

                    // we need to pop again to remove the previous '('
                    leftElement = operations.pop();

                    if(regex.charAt(leftElement) == '|'){
                        throw new IllegalArgumentException("Invalid regular expression: not supporting multiple | without parentheses");
                    }

                    //edge from left ( to the first element after |
                    graph.addEdge(leftElement, op + 1);
                    //edge from | to the current )
                    graph.addEdge(op, i);

                } else if (regex.charAt(op) == '(') {
                    leftElement = op;
                } else {
                    /*
                        should never happen, as we only push ( and | on stack.
                        If it happens, then it is a bug in the code.
                     */
                    assert false;
                }
            }

            // closure operator * (uses 1-character lookahead)
            if (i < regex.length() - 1 && regex.charAt(i + 1) == '*') {
                /*
                    create a cycle between * in next position i+1 and current expression.
                    Note, cannot just link i with i+1, because, if previous
                    element was a (...) expression, we need to point to its
                    opening '(' and not what is at position i (ie, the closing ')')
                 */
                graph.addEdge(leftElement, i + 1);
                graph.addEdge(i + 1, leftElement);
            }

            if (current == '(' || current == '*' || current == ')') {
                graph.addEdge(i, i + 1);
            }

            /*
                Note: there is nothing to do if char is different from *, (, |, and ),
                as here we deal only with the empty-transitions
             */
        }

        if (! operations.isEmpty()) {
            //there should be the right number of ( matched by ), in the right order
            throw new IllegalArgumentException("Invalid regular expression: not closed parentheses '('");
        }

        return graph;
    }

    /**
     * Check if the input text does match this regular expression.
     */
    public boolean match(String txt) {
        Objects.requireNonNull(txt);

        /*
         *  Recall, we have N+1 vertices:
         *  0 to N-1 for each position in the regex,
         *  and N for the final acceptance state
         */
        int acceptanceState = regex.length();

        /*
            Check what we can reach in the FSM from the beginning
            without even needed to read any char from the text.
            We need to keep track of all possible non-deterministic
            paths we can take on the FSM.
         */
        Set<Integer> vertices = nfsm.findConnected(0);

        for (int i = 0; i < txt.length(); i++) {

            char current = txt.charAt(i);

            /*
                Let's keep it simple, and avoid having to deal how to escape meta-characters
                in the regex
             */
            if (current == '*' || current == '|' || current == '(' || current == ')') {
                throw new IllegalArgumentException("text contains the metacharacter '" + txt.charAt(i) + "'");
            }

            /*
                For all non-deterministic paths, check if current element in the
                text does match the char in the vertex, ie the element in the regex.
             */
            Set<Integer> match = new HashSet<>();

            for (int v : vertices) {
                if (v == acceptanceState) {
                    /*
                        Note: we still need to read the whole text,
                        to see if all of it does match
                      */
                    continue;
                }
                if ((regex.charAt(v) == txt.charAt(i)) || regex.charAt(v) == '.') {
                    /*
                        If there is a match, then on such path we can move to the
                        next element on the regex.
                        Note: '.' is always matched, as it means any character
                     */
                    match.add(v + 1);
                }
            }

            if(match.isEmpty()){
                /*
                    No match in any of the non-deterministic paths.
                    So we are done.
                 */
                return false;
            }

            /*
                Besides the next elements v+1 expected in the paths (ie what stored
                in the 'match' set), also compute all possible non-deterministic paths that can
                be taken without reading from text, by doing empty-transitions

             */
            vertices = nfsm.findConnected(match);
        }

        /*
            this is checked only when the whole text has been read.
            If when we have read the whole text we have at least one path
            that is in the acceptance state, then the text does match the regex.
         */
        return vertices.contains(acceptanceState);
    }

}
