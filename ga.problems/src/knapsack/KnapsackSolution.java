package knapsack;

import ga.framework.model.*;
import ga.framework.operators.FitnessEvaluator;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

import java.util.ArrayList;
import java.util.List;

public class KnapsackSolution extends Solution {
    List<Item> items = new ArrayList<>();

    @Override
    public void setFitness(double fitness) {
        super.setFitness(items.stream()
                .map(Item::getValue)
                .reduce(0.0, Double::sum));
    }

    public KnapsackSolution(Problem problem) {
        super(problem);
    }
    public KnapsackSolution(Solution toCopy) {
        super(toCopy);
    }
}
