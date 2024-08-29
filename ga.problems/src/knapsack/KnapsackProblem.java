package knapsack;

import ga.framework.model.*;
import ga.framework.operators.*;

import java.util.List;

public class KnapsackProblem implements Problem {
    private final int capacity;

    private List<Item> items;




    public KnapsackProblem(int capacity, List<Item> items) {
        this.capacity = capacity;
    }

    @Override
    public KnapsackSolution createNewSolution() throws NoSolutionException {
        return new KnapsackSolution(this);
    }
}
