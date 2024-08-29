package knapsack;

import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.EvolutionException;
import ga.framework.operators.EvolutionaryOperator;

import java.util.List;

public class KnapsackMutation implements EvolutionaryOperator {

    KnapsackProblem problem;

    private KnapsackSolution solutionRemoveItem(KnapsackSolution ourSolution){


        Item item = ourSolution.items.get((int )(Math.random() * ourSolution.items.size()));
        ourSolution.items.remove(item);

        return ourSolution;
    }

    private KnapsackSolution solutionAddItem(KnapsackSolution ourSolution) {

        List<Item> itemsPass = this.problem.itemsOutside.stream()
                .filter(x -> {
                    return x.weight + this.problem.placeTaken <= this.problem.capacity;
                }).toList();
        Item item = itemsPass.get((int )(Math.random() * itemsPass.size()));
        ourSolution.items.add(item);
        return ourSolution;
    }

    public KnapsackMutation(KnapsackProblem problem) {
        this.problem = problem;
    }

    @Override
    public Solution evolve(Solution solution) throws EvolutionException {
        double fitness = solution.getFitness();
        int index = -1;
        KnapsackSolution newSolution = this.problem.currPop.get((int) (Math.random() * this.problem.currPop.size()));
        for(int i = 0; i < this.problem.currPop.size(); i++){
            if(Double.compare(fitness , this.problem.currPop.get(i).getFitness()) == 0){
                index = i;
                newSolution = this.problem.currPop.get(i);
                i = this.problem.currPop.size();
            }
        }

        this.problem.currPop.remove(index);
        return evolveHelper(newSolution);
    }
    private KnapsackSolution evolveHelper(KnapsackSolution solution) throws EvolutionException {


        if(!solution.items.isEmpty() && !this.problem.itemsOutside.isEmpty()){
            if(Math.random() >= 0.5){
                return solutionRemoveItem(solution);
            } else return solutionAddItem(solution);

        } else if (!this.problem.itemsOutside.isEmpty()) {
            return solutionAddItem(solution);

        } else if (!solution.items.isEmpty()) {
            return solutionRemoveItem(solution);

        } else throw new EvolutionException("No items found during evolution");
    }
}
