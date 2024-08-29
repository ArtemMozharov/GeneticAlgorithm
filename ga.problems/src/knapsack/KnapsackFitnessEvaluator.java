package knapsack;

import ga.framework.model.*;
import ga.framework.operators.*;

import java.util.ArrayList;
import java.util.List;

public class KnapsackFitnessEvaluator implements FitnessEvaluator{
    public void evaluate(List<Solution> population) {
        population.stream().map(Solution::getFitness).forEach(System.out::println);
    }
    public static Solution computeFitness(KnapsackSolution knapsackSolution){
        double fitness = knapsackSolution.items.stream()
                .map(Item::getValue).reduce(0.0, Double::sum);
        knapsackSolution.setFitness(fitness);
        return knapsackSolution;
    }
}
