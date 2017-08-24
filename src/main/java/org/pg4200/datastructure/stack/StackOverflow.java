package org.pg4200.datastructure.stack;

/**
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
	at org.pg4200.datastructure.stack.StackOverflow.b(StackOverflow.java:22)
	at org.pg4200.datastructure.stack.StackOverflow.a(StackOverflow.java:16)
	at org.pg4200.datastructure.stack.StackOverflow.b(StackOverflow.java:22)
	at org.pg4200.datastructure.stack.StackOverflow.a(StackOverflow.java:16)
	at org.pg4200.datastructure.stack.StackOverflow.b(StackOverflow.java:22)
	at org.pg4200.datastructure.stack.StackOverflow.a(StackOverflow.java:16)
	at org.pg4200.datastructure.stack.StackOverflow.b(StackOverflow.java:22)
	at org.pg4200.datastructure.stack.StackOverflow.a(StackOverflow.java:16)
	at org.pg4200.datastructure.stack.StackOverflow.b(StackOverflow.java:22)
	at org.pg4200.datastructure.stack.StackOverflow.a(StackOverflow.java:16)
	at org.pg4200.datastructure.stack.StackOverflow.b(StackOverflow.java:22)
	at org.pg4200.datastructure.stack.StackOverflow.a(StackOverflow.java:16)
	at org.pg4200.datastructure.stack.StackOverflow.b(StackOverflow.java:22)
	at org.pg4200.datastructure.stack.StackOverflow.a(StackOverflow.java:16)
	at org.pg4200.datastructure.stack.StackOverflow.b(StackOverflow.java:22)
	at org.pg4200.datastructure.stack.StackOverflow.a(StackOverflow.java:16)
	...
 */
