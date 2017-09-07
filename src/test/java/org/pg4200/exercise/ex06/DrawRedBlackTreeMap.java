package org.pg4200.exercise.ex06;

import org.pg4200.datastructure.map.tree.RedBlackTreeMap;

/**
 * Created by arcuri82 on 06-Sep-17.
 */
public class DrawRedBlackTreeMap<K extends Comparable<K>, V> extends RedBlackTreeMap<K, V> {

    /*
                         [0]
                        /  \
                      /     \
                    (1)     (2)
                   /  \    /  \
                 [3]  [4][5]  [6]
     */


    /**
     * Strong assumption: only going to insert/remove key integers from 0 to 6.
     * This means each key is at most 1 character, and tree has at most a depth
     * of 3.
     */
    public void draw() {

        int depth = getMaxTreeDepth();

        if (depth == 0) {
            drawWhenEmpty();
        } else if (depth == 1) {
            drawDepth1();
        } else if (depth == 2) {
            drawDepth2();
        } else if (depth == 3) {
            drawDepth3();
        } else {
            throw new IllegalStateException("Cannot handle depth " + depth);
        }
    }

    private void drawDepth1() {
        print(nodeAsString(root) + "\n");
    }

    private void drawDepth2() {

        //first line
        print(spaces(3) + nodeAsString(root) + "\n");

        //second line
        print(spaces(2));
        print(root.left != null ? "/" : spaces(1));
        print(spaces(3));
        print(root.right != null ? "\\" : spaces(1));
        print("\n");

        //third line
        print(root.left != null ? nodeAsString(root.left) : spaces(3));
        print(root.right != null ? spaces(3) + nodeAsString(root.right) : spaces(0));
        print("\n");
    }

    private void drawDepth3() {

        //first line
        print(spaces(9) + nodeAsString(root) + "\n");

        boolean left = root.left != null;
        boolean right = root.right != null;
        boolean leftleft = left && root.left.left != null;
        boolean leftright = left && root.left.right != null;
        boolean rightleft = right && root.right.left != null;
        boolean rightright = right && root.right.right != null;

        //second line
        print(spaces(8));
        print(left  ? "/" : spaces(1));
        print(spaces(3));
        print(right ? "\\" : spaces(1));
        print("\n");

        //third line
        print(spaces(7));
        print(left  ? "/" : spaces(1));
        print(spaces(5));
        print(right ? "\\" : spaces(1));
        print("\n");

        //4th line
        print(spaces(4));
        print(left  ? nodeAsString(root.left) : spaces(3));
        print(spaces(4));
        print(right ? spaces(3) + nodeAsString(root.right) : spaces(0));
        print("\n");


        //5th line
        print(spaces(3));
        print(leftleft ? "/" : spaces(1));
        print(spaces(3));
        print(leftright ? "\\" : spaces(1));
        print(spaces(5));
        print(rightleft ? "/" : spaces(1));
        print(spaces(3));
        print(rightright ? "\\" : spaces(0));
        print("\n");

        //6th line
        print(leftleft ? nodeAsString(root.left.left) : spaces(3));
        print(spaces(4));
        print(leftright ? nodeAsString(root.left.right) : spaces(3));
        print(spaces(1));
        print(rightleft ? nodeAsString(root.right.left) : spaces(3));
        print(spaces(3));
        print(rightright ? nodeAsString(root.right.right) : spaces(0));
        print("\n");
    }

    private String spaces(int n){
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<n; i++){
            buffer.append(" ");
        }
        return buffer.toString();
    }

    private void print(String s) {
        System.out.print(s);
    }

    private String nodeAsString(TreeNode node) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(node.is_red ? "(" : "[");
        buffer.append(node.key.toString());
        buffer.append(node.is_red ? ")" : "]");
        return buffer.toString();
    }

    private void drawWhenEmpty() {
        print("-\n");
    }


    public static void main(String[] args){

        //TODO sequence of operations on RBT

        DrawRedBlackTreeMap<Integer,String>  tree = new DrawRedBlackTreeMap<>();

        tree.draw();
        System.out.print("\n\n\n");

        tree.put(2,"a");
        tree.draw();
        System.out.print("\n\n\n");

        tree.put(1,"a");
        tree.draw();
        System.out.print("\n\n\n");

        tree.put(0,"a");
        tree.draw();
        System.out.print("\n\n\n");

        tree.put(3,"a");
        tree.draw();
        System.out.print("\n\n\n");

        tree.put(4,"a");
        tree.draw();
        System.out.print("\n\n\n");

        tree.put(5,"a");
        tree.draw();
        System.out.print("\n\n\n");

        tree.put(6,"a");
        tree.draw();
        System.out.print("\n\n\n");

        tree.put(7,"a");
        tree.draw();
        System.out.print("\n\n\n");
    }

}
