package knapsack;

import ga.framework.model.*;
import ga.framework.operators.*;

import java.util.ArrayList;
import java.util.List;

public class KnapsackFitnessEvaluator implements FitnessEvaluator{
    public void evaluate(List<Solution> population) {
        population.stream().map(Solution::getFitness).forEach(System.out::println);
    }
}
