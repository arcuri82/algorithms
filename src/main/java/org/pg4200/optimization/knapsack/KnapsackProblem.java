package org.pg4200.optimization.knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class KnapsackProblem {

    private final double[] weights;

    private final double[] values;

    private final double limit;

    private transient List<Item> items;


    public static class Item {
        private final double weight;
        private final double value;
        private final int index;


        public Item(double weight, double value, int index) {
            this.weight = weight;
            this.value = value;
            this.index = index;
        }

        public double getWeight() {
            return weight;
        }

        public double getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }
    }


    public KnapsackProblem(double limit, double[] weights, double[] values) {
        this.limit = limit;
        this.weights = Objects.requireNonNull(weights).clone();
        this.values = Objects.requireNonNull(values).clone();

        if (weights.length != values.length) {
            throw new IllegalArgumentException("Mismatch between weights and values length: " +
                    weights.length + " != " + values.length);
        }
    }

    public List<Item> getCopyOfItems() {

        if (items == null) {
            items = new ArrayList<>();
            for (int i = 0; i < getSize(); i++) {
                items.add(new Item(weights[i], values[i], i));
            }
        }

        return new ArrayList<>(items);
    }

    public int getSize() {
        return weights.length;
    }

    public double evaluate(boolean[] selection) {
        Objects.requireNonNull(selection);

        if (selection.length != weights.length) {
            throw new IllegalArgumentException("Invalid length of input parameter");
        }

        double objective = 0d;
        double weight = 0d;

        for (int i = 0; i < selection.length; i++) {
            if (selection[i]) {
                objective += values[i];
                weight += weights[i];

                if (weight > limit) {
                    return 0d;
                }
            }
        }

        return objective;
    }
}
