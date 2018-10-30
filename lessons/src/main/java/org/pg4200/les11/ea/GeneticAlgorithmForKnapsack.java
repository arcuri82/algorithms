package org.pg4200.les11.ea;

import org.pg4200.les10.knapsack.KnapsackProblem;

import java.util.*;

public class GeneticAlgorithmForKnapsack {

    /*
        Internal class used to represent an individual,
        ie, a solution in the search space.
        We also keep track of its fitness function,
        as to avoid recalculating each time we need it.
     */
    private static class Individual {
        public double fitness;
        public boolean[] chromosome;
        private final KnapsackProblem problem;

        public Individual(boolean[] chromosome, KnapsackProblem problem) {
            this(chromosome, problem, problem.evaluate(chromosome));
        }

        private Individual(boolean[] chromosome, KnapsackProblem problem, double fitness) {
            this.chromosome = chromosome;
            this.fitness = fitness;
            this.problem = problem;
        }

        public void updateFitness() {
            fitness = problem.evaluate(chromosome);
        }

        public Individual copy() {
            return new Individual(chromosome.clone(), problem, fitness);
        }
    }

    public static boolean[] solve(int maxIterations, KnapsackProblem problem) {

        if (maxIterations < 1) {
            throw new IllegalArgumentException("Invalid number of iterations");
        }
        Objects.requireNonNull(problem);

        Random random = new Random();

        final int n = problem.getSize();
        final int populationSize = 100;

        int counter = 0;

        List<Individual> population = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize && counter < maxIterations; i++) {
            population.add(new Individual(sample(n, random), problem));
            counter++;
        }

        while (counter < maxIterations - 1) {
            //generations loop

            List<Individual> next = new ArrayList<>(populationSize);

            /*
                Let's save 10% of the population.
                You can think of them like Vampires, forever young, living throughout
                different generations without changing (and occasionally mating with
                the "humans", ie the other individuals in the population).
              */
            elitism(next, population, populationSize / 10);

            //next generation
            while (counter < maxIterations - 1 && next.size() < populationSize - 1) {
                Individual parent_0 = tournament(population, random);
                Individual parent_1 = tournament(population, random);

                Individual[] offspring = mate(parent_0, parent_1, random);

                for (Individual os : offspring) {
                    mutate(os, random);

                    /*
                        I need it here, as easily can exceed weight limit
                        with xover and mutation
                     */
                    repair(os, problem);
                    os.updateFitness();
                    counter++;

                    next.add(os);
                }
            }

            /*
                Instance of Ættestup... just kill the old population
             */
            population = next;
        }

        assert population.size() > 0;

        return population.stream()
                /*
                    Note: "max" is a terminal operation that returns an Optional.
                    It has to be an Optional, as "max" would be undefined on
                    an empty collection.
                 */
                .max(Comparator.comparingDouble(i -> i.fitness))
                //"map" here is on the Optional, not the stream
                .map(i -> i.chromosome)
                .get();
    }

    private static void elitism(List<Individual> next, List<Individual> population, int k) {

        population.sort(Comparator.comparingDouble(i -> i.fitness));

        for (int j = population.size() - 1; next.size() < k; j--) {
            next.add(population.get(j));
        }
    }

    private static void repair(Individual individual, KnapsackProblem problem) {

        double weight = problem.calculateWeight(individual.chromosome);
        double limit = problem.getLimit();

        while(weight > limit){

            for(int i=0; i<individual.chromosome.length; i++){
                if(individual.chromosome[i]){
                    individual.chromosome[i] = false;
                    break;
                }
            }

            weight = problem.calculateWeight(individual.chromosome);
        }
    }

    private static void mutate(Individual individual, Random random) {

        final int n = individual.chromosome.length;
        final double p = 1d / (double) n;

        for (int j = 0; j < n; j++) {
            if (random.nextDouble() < p) {
                individual.chromosome[j] = !individual.chromosome[j];
            }
        }
        /*
            Note: here it is OK if an offspring is not mutated,
            because anyway we do use xover.
            And even if no change at all (ie mutation/xover),
            having non-changed individual can still be good
            in some cases in the following generations (eg, to
            avoid local optima).
         */
    }

    private static Individual[] mate(Individual parent_0, Individual parent_1, Random random) {

        final int n = parent_0.chromosome.length;

        assert parent_1.chromosome.length == n;

        /*
            Choose a random splitting point, but no first, and not last
         */
        int splitPoint = 1 + random.nextInt(n - 2);

        Individual[] offspring = {
                parent_0.copy(),
                parent_1.copy()
        };

        double xoverProbability = 0.7;

        if (random.nextDouble() < xoverProbability) {
            for (int i = 0; i < splitPoint; i++) {
                offspring[0].chromosome[i] = parent_1.chromosome[i];
                offspring[1].chromosome[i] = parent_0.chromosome[i];
            }
        }

        return offspring;
    }

    private static Individual tournament(List<Individual> population, Random random) {

        Individual best = population.get(random.nextInt(population.size()));

        for (int i = 0; i < 6; i++) {
            Individual opponent = population.get(random.nextInt(population.size()));
            if (opponent.fitness > best.fitness) {
                best = opponent;
            }
        }

        return best;
    }

    private static boolean[] sample(int n, Random random) {

        boolean[] solution = new boolean[n];
        for (int i = 0; i < solution.length; i++) {
            solution[i] = random.nextBoolean();
        }

        return solution;
    }

}
