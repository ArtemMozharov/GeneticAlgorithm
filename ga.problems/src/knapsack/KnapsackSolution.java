package knapsack;

import ga.framework.model.*;

import java.util.ArrayList;
import java.util.List;

public class KnapsackSolution extends Solution {
    double knapsackSolutionFitness(){
        return this.items.stream()
                .map(Item::getValue)
                .reduce(0.00, Double::sum);
    }
    public List<Item> getItems() {
        return items;
    }
    List<Item> items = new ArrayList<>();

    public KnapsackSolution(Problem problem) {
        super(problem);
        super.
    }
    public KnapsackSolution(Solution toCopy) {
        super(toCopy);
        super.setFitness(knapsackSolutionFitness());
    }

    public KnapsackProblem getProblem() {
        return (KnapsackProblem) super.getProblem();
    }
}
