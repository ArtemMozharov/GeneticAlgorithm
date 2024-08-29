package knapsack;

import ga.framework.model.NoSolutionException;
import ga.framework.model.Problem;
import ga.framework.model.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KnapsackSolutionTest {

    @org.junit.jupiter.api.Test
    void getFitness() throws NoSolutionException {
        Item item1 = new Item(2.00, 3.00);
        Item item2 = new Item(2.00, 4.00);
        Item item3 = new Item(2.00, 5.00);
        List<Item> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        KnapsackProblem problem = new KnapsackProblem(4, list);
        List<Solution> listSolutions = new ArrayList<>();
        listSolutions.add(KnapsackFitnessEvaluator.computeFitness(problem.createNewSolution()));
        KnapsackFitnessEvaluator evaluator = new KnapsackFitnessEvaluator();
        evaluator.evaluate(listSolutions);
    }
}