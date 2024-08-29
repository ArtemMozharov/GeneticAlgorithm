package knapsack;

import ga.framework.model.*;
import ga.framework.operators.*;

public class KnapsackProblem implements Problem {
    private final int capacity;
    private int placeTaken;

    public KnapsackProblem(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public KnapsackSolution createNewSolution() throws NoSolutionException {
        return new KnapsackSolution(this);
    }
}
