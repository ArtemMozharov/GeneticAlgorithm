package knapsack;

import ga.framework.model.*;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Copy;

import java.util.ArrayList;
import java.util.List;

public class KnapsackSolution extends Solution {
    List<Item> items = new ArrayList<>();

    public KnapsackSolution(Problem problem) {
        super(problem);
    }
    public KnapsackSolution(Solution toCopy) {
        super(toCopy);
    }
}
