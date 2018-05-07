package org.pg4200.les10.knapsack;

import java.util.List;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class GreedyForKnapsack {

    private static boolean[] solve(KnapsackProblem problem, List<KnapsackProblem.Item> sortedItems){

        boolean[] result = new boolean[problem.getSize()];

        double fitness = problem.evaluate(result);

        for(int i=0; i < sortedItems.size(); i++){

            KnapsackProblem.Item x = sortedItems.get(i);

            result[x.getIndex()] = true;

            double evaluated = problem.evaluate(result);

            if(evaluated < fitness){
                result[x.getIndex()] = false;
                break;
            }

            fitness = evaluated;
        }

        return result;
    }

    public static boolean[] solveByHeavierFirst(KnapsackProblem problem){

        List<KnapsackProblem.Item>  items = problem.getCopyOfItems();

        items.sort((a,b) -> {
            double diff = a.getWeight() - b.getWeight();
            if(diff < 0){
                return 1;
            } else if(diff > 0){
                return -1;
            } else {
                return 0;
            }
        });

        return solve(problem, items);
    }


    public static boolean[] solveByLighterFirst(KnapsackProblem problem){

        List<KnapsackProblem.Item>  items = problem.getCopyOfItems();

        items.sort((a,b) -> {
            double diff = a.getWeight() - b.getWeight();
            if(diff > 0){
                return 1;
            } else if(diff < 0){
                return -1;
            } else {
                return 0;
            }
        });

        return solve(problem, items);
    }


    public static boolean[] solveByBestRatioFirst(KnapsackProblem problem){

        List<KnapsackProblem.Item>  items = problem.getCopyOfItems();

        items.sort((a,b) -> {

            if(a.getWeight() == 0){
                return -1;
            }
            if(b.getWeight() == 0){
                return 1;
            }

            double ra = a.getValue() / a.getWeight();
            double rb = b.getValue() / b.getWeight();

            double diff = ra - rb;

            if(diff < 0){
                return 1;
            } else if(diff > 0){
                return -1;
            } else {
                return 0;
            }
        });

        return solve(problem, items);
    }

}
