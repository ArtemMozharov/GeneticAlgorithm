package knapsack;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackSolutionTest {

    @org.junit.jupiter.api.Test
    void getFitness() {
        Item item1 = new Item(2.00, 3.00);
        Item item2 = new Item(2.00, 4.00);
        Item item3 = new Item(2.00, 5.00);
        KnapsackSolution solution = new KnapsackSolution(new KnapsackProblem(100, Arrays.asList(item1, item2, item3))
        );

        System.out.println(solution.items);
        System.out.println(solution.getFitness());
    }
}