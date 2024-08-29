package knapsack;

import ga.framework.model.*;
import ga.framework.operators.FitnessEvaluator;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

import java.util.ArrayList;
import java.util.List;

public class KnapsackSolution extends Solution {
    List<Item> items = new ArrayList<>();
    private KnapsackProblem knapsackProblem;

    public KnapsackProblem getKnapsackProblem() {
        return knapsackProblem;
    }

    @Override
    public void setFitness(double fitness) {
        super.setFitness(items.stream()
                .map(Item::getValue)
                .reduce(0.0, Double::sum));
    }

    public KnapsackSolution(Problem problem) {
        super(problem);
    }

    public KnapsackSolution(KnapsackProblem problem) {
        super(problem);
        this.knapsackProblem = problem;
    }

    public KnapsackSolution(Solution toCopy) {
        super(toCopy);
    }
}
