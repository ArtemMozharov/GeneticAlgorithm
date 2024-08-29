package knapsack;

import ga.framework.model.*;

import java.util.List;

public class KnapsackSolution extends Solution {
    int size;

    public List<Item> getItems() {
        return items;
    }

    List<Item> items;

    public KnapsackSolution(Problem problem) {
        super(problem);
    }

    public KnapsackSolution(Solution toCopy) {
        super(toCopy);
    }

    public KnapsackProblem getProblem() {
        return (KnapsackProblem) super.getProblem();
    }
}
