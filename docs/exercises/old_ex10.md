# Exercise 10

## Part A

The code of `RandomForKnapsack` is not particularly good, as it creates
new arrays on the heap at each step.
Write a new `RandomForKnapsackOptimized` class, in which __ONLY 2__ arrays
are instantiated: one to keep the best solution seen so far, and one as
a buffer to randomize at each step to evaluate a new configuration.
When a new, better configuration is found, just swap the two array pointers.

Create a new `RandomForKnapsackOptimizedTest`, based on `RandomForKnapsackTest`,
to test this new implementation. 

## Part B

Consider the class `HillClimbingForQueens`. 
Modify the Hill Climbing algorithm to use *Steepest Ascent*:
instead of climbing to the first better element in the neighbourhood,
first look at all the candidate solutions in the neighbourhood, and
then move toward the best.
Implement such new variant in a class called `SteepestAscentHCforQueens`.

Write a test class `SteepestAscentHCforQueensTest` in which you test your
steepest ascent version on boards of size 8, 16, 20 and 100.

## Solutions

Solutions to this exercise can be found in the `solutions`
module, under the `org.pg4200.oldsol10` package.