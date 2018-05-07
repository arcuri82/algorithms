package org.pg4200.les11.randomness;

/**
 * Created by arcuri82 on 16-Oct-17.
 */
public interface MyRandom {

    void setSeed(int seed);

    int nextPositive();
}
