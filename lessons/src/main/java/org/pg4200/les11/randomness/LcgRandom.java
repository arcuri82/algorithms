package org.pg4200.les11.randomness;

/**
 * Created by arcuri82 on 16-Oct-17.
 */
public class LcgRandom implements MyRandom {

    private int current;

    public LcgRandom(){
        setSeed(Math.abs((int) System.currentTimeMillis()));
    }

    @Override
    public void setSeed(int seed) {
        current = seed;
    }

    @Override
    public int nextPositive() {

        /*
            Note: here we need to use longs, as otherwise
            multiplication * would overflow.

            These "a" and "c" values have special properties
         */
        long a = 1_103_515_245;
        long c = 12_345;
        long m = Integer.MAX_VALUE; //ie, 2^31 - 1

        // no info is lost with casting (int), as anyway we do "% m"
        current = (int)((a * current + c) % m);

        return current;
    }

    public static void main(String[] args){

        MyRandom rand = new LcgRandom();
        for(int i=0; i<30; i++){
            //recall, when using %, we might lose randomness
            System.out.print("  " + rand.nextPositive() % 10);
        }
        System.out.println();
    }
}
