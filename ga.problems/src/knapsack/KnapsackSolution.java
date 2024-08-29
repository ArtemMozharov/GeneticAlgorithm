package knapsack;

import ga.framework.model.*;
import ga.framework.operators.*;

import java.util.List;

public class KnapsackSolution extends Solution {
    int size;
    List<Item> items;

    public KnapsackSolution(Problem problem) {
        super(problem);
    }

    public KnapsackSolution(Solution toCopy) {
        super(toCopy);
    }
}
