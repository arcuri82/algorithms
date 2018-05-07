package org.pg4200.les11.randomness;

/**
 * Created by arcuri82 on 16-Oct-17.
 */
public class ArrayRandom implements MyRandom {

    private int index;

    private final int[] values = {7,2,4,1,1,3,7,1,4,9};

    public ArrayRandom(){
        setSeed((int) System.currentTimeMillis());
    }


    @Override
    public void setSeed(int seed) {
        index = Math.abs(seed) % values.length;
    }

    @Override
    public int nextPositive() {

        int x = values[index];
        index = (index + 1) % values.length;

        return x;
    }

    public static void main(String[] args){

        MyRandom rand = new ArrayRandom();
        for(int i=0; i<30; i++){
            System.out.print("  " + rand.nextPositive());
        }
        System.out.println();
    }
}
