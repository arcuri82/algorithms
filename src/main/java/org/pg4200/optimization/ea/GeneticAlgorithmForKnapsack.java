package org.pg4200.optimization.ea;

import org.pg4200.optimization.knapsack.KnapsackProblem;

import java.util.Objects;
import java.util.Random;

public class GeneticAlgorithmForKnapsack {

    private static class Individual{
        public double fitness;
        public boolean[] chromosome;

        public Individual(boolean[] chromosome, KnapsackProblem problem) {
            this.chromosome = chromosome;
            this.fitness = problem.evaluate(chromosome);
        }

        private Individual(boolean[] chromosome, double fitness) {
            this.chromosome = chromosome;
            this.fitness = fitness;
        }


        public Individual copy(){
            return new Individual(chromosome.clone(), fitness);
        }
    }

    public static boolean[] solve(int maxIterations, KnapsackProblem problem){

        if(maxIterations < 1){
            throw new IllegalArgumentException("Invalid number of iterations");
        }
        Objects.requireNonNull(problem);

        Random random = new Random();

        final int n = problem.getSize();
        final double p = 1d / (double) n;

        int counter = 0;

        Individual[] population = new Individual[50];
        for(int i=0; i<population.length && counter < maxIterations; i++){
            population[i] = new Individual(sample(n, random), problem);
            counter++;
        }

        while(counter < maxIterations - 1){



            Individual parent_0 = tournament(population, random);
            Individual parent_1 = tournament(population, random);

            Individual[] offspring = mate(parent_0, parent_1);

//            mutate(offspring[0]);
//            mutate(offspring[1]);


            //TODO
        }

        return null; //TODO
    }

    private static Individual[] mate(Individual parent_0, Individual parent_1) {
        return new Individual[0]; //TODO
    }

    private static Individual tournament(Individual[] population,  Random random){

        Individual best = population[random.nextInt(population.length)];

        for(int i=0; i<6; i++){
            Individual opponent = population[random.nextInt(population.length)];
            if(opponent.fitness > best.fitness){
                best = opponent;
            }
        }

        return best;
    }

    private static boolean[] sample(int n, Random random){

        boolean[] solution = new boolean[n];
        for(int i=0; i<solution.length; i++){
            solution[i] = random.nextBoolean();
        }

        return solution;
    }

}
