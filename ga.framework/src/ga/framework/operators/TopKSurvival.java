package ga.framework.operators;

import ga.framework.model.Solution;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TopKSurvival implements SurvivalOperator{
    private final int k;
    public TopKSurvival(int k){
        this.k = k;
    }
    @Override
    public List<Solution> selectPopulation(List<Solution> candidates, int populationSize) throws SurvivalException {
        if (populationSize < k){
            throw new SurvivalException("K ist größer als Population");
        }
        // Top k elements:
        List<Solution> solutions = new java.util.ArrayList<>(candidates.stream()
                .sorted(Comparator.comparing(Solution::getFitness))
                .limit(k)
                .toList());
        // Random elements:
        if (k<populationSize){
            while (solutions.size()<populationSize){
                Random random = new Random();
                solutions.add(candidates.get(random.nextInt(candidates.size())));
            }
        }
        return solutions;
    }
}
