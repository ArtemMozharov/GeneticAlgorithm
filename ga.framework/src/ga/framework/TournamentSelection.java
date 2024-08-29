package ga.framework;

import ga.framework.model.Solution;
import ga.framework.operators.SelectionOperator;

import java.util.List;
import java.util.Random;

public class TournamentSelection implements SelectionOperator {
    @Override
    public Solution selectParent(List<Solution> candidates) {
        Random random = new Random();
        Solution solution1 = candidates.get(random.nextInt(candidates.size()));
        Solution solution2 = candidates.get(random.nextInt(candidates.size()));
        if (solution1.getFitness()>solution2.getFitness()){
            return solution1;
        }
        if (solution2.getFitness()>solution1.getFitness()){
            return solution2;
        }
        else {
            return solution1;
        }
    }
}
