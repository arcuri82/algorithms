package org.pg4200.les02.stack;

/**
 * Code here ends up in a infinite loop, where a new method call is done
 * while the current one is not completed yet.
 * As each call push a new frame on the method-call-stack, the JVM process
 * will run out of memory on the stack, and throw an error
 *
 * Created by arcuri82 on 23-Aug-17.
 */
public class StackOverflow {

    public static void main(String[] args){
        a(0);
    }

    public static int a(int x){
        x++;

        x = b(x);

        return x;
    }

    public static int b(int y){
        return a(y);
    }
}
/*
Exception in thread "main" java.lang.StackOverflowError
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	at org.pg4200.les02.stack.StackOverflow.a(StackOverflow.java:20)
	at org.pg4200.les02.stack.StackOverflow.b(StackOverflow.java:26)
	...
 */
