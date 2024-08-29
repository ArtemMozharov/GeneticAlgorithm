package knapsack;

import ga.framework.GeneticAlgorithm;
import ga.framework.TournamentSelection;
import ga.framework.operators.SurvivalOperator;
import ga.framework.operators.TopKSurvival;

import java.util.Arrays;

public class ConcreteProblem
{
    public static void main(String[] args) {
        Item g1 = new Item(5, 10);
        Item g2 = new Item(4, 8);
        Item g3 = new Item(4, 6);
        Item g4 = new Item(4, 4);
        Item g5 = new Item(3, 7);
        Item g6 = new Item(3, 4);
        Item g7 = new Item(2, 6);
        Item g8 = new Item(2, 3);
        Item g9 = new Item(1, 3);
        Item g10 = new Item(1, 1);

        KnapsackProblem problem = new KnapsackProblem(11, Arrays.asList(g1, g2, g3, g4, g5, g6, g7, g8, g9, g10));
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        geneticAlgorithm.solve(problem)
                .withPopulationOfSize(4)
                .evolvingSolutionsWith(KnapsackProblem.knapsackMutation)
                .evaluatingSolutionsBy(new KnapsackFitnessEvaluator())
                .survivalOperatorIs(new TopKSurvival(3))
                .performingSelectionWith(new TournamentSelection());
    }
}
