package org.pg4200.les03.analysis;

import java.util.Random;

/**
 * Created by arcuri82 on 18-Aug-17.
 */
public class Sum {

    public static int sum(int[] array){

        int sum = 0;
        for(int i=0; i<array.length; i++){
            sum += array[i];
        }
        return sum;
    }

    public static int pairs(int[] array){

        int pairs = 0;

        for(int i=0; i<array.length; i++){
            for(int j=0; j<array.length; j++){
                if(i!=j && array[i] == array[j]){
                    pairs++;
                }
            }
        }

        return pairs;
    }


    public static int pairsOptimized(int[] array){

        int pairs = 0;

        //if a[i] == a[j], then it implies a[j] == a[i]

        for(int i=0; i<array.length -1; i++){
            for(int j=i+1; j<array.length; j++){
                if(array[i] == array[j]){
                    pairs++;
                }
            }
        }

        return pairs * 2;
    }

    public static void doSomething(int[] array){

        for(int i=0; i<array.length; i++){
            for(int j=i; j<array.length; j++){
                //... something
            }
        }
    }


    private static int[] randomArray(int size, int max){
        Random random = new Random();
        int[] array = new int[size];
        for(int i=0; i<array.length; i++){
            array[i] = random.nextInt(max);
        }
        return array;
    }

    public static void main(String[] args){

        /*
            analyzing execution time is tricky in Java, due to
            JIT (Just In Time compiler).
            The JVM interprets the bytecode, but, if it sees that
            a method is called a lot, it might decide to optimize
            it on the fly, and even compile it on the fly to
            machine code.

            So here, to avoid the difference in runtime with the
            before optimization phase, we do a "warmup" to try
            to force JIT. But we cannot be sure.
            Note: what we do here is just a SIMPLE example of
            checking execution times for educational purposes,
            not a precise method to benchmarking Java programs
         */
        int[] warmupArray = new int[100];

        for(int i=0; i<1_000_000; i++){
            pairs(warmupArray);
        }

        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800};
        int repetitions = 100;

        for(int n : sizes){

            int[] data = randomArray(n, 100);
            long start = System.currentTimeMillis();
            for(int j=0; j<repetitions; j++){
                pairs(data);
            }
            long delta = System.currentTimeMillis() - start;
            double avg_seconds = delta  / 1_000d;

            System.out.println("N=" + n + "\t seconds="+avg_seconds);
        }
        /*
            On my machine, repeated 100 times:

            N=100	 seconds=0.005
            N=200	 seconds=0.005
            N=400	 seconds=0.012
            N=800	 seconds=0.072
            N=1600	 seconds=0.211
            N=3200	 seconds=0.754
            N=6400	 seconds=2.829
            N=12800	 seconds=11.48
         */
    }


}
