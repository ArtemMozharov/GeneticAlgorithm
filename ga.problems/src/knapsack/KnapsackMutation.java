package knapsack;

import ga.framework.model.Problem;
import ga.framework.model.Solution;
import ga.framework.operators.EvolutionException;
import ga.framework.operators.EvolutionaryOperator;

import java.util.List;

public class KnapsackMutation implements EvolutionaryOperator {

    private KnapsackSolution solutionRemoveItem(KnapsackSolution ourSolution){


//        Item item = ourSolution.items.get((int )(Math.random() * ourSolution.items.size()));
//        ourSolution.items.remove(item);

        return ourSolution;
    }

    private KnapsackSolution solutionAddItem(KnapsackSolution ourSolution) {

//        List<Item> itemsPass = ourSolution.getKnapsackProblem().itemsOutside.stream()
//                .filter(x -> {
//                    return x.weight + ourSolution.getKnapsackProblem().placeTaken <= ourSolution.getKnapsackProblem().capacity;
//                }).toList();
//        Item item = itemsPass.get((int )(Math.random() * itemsPass.size()));
//        ourSolution.items.add(item);
        return ourSolution;
    }



    @Override
    public Solution evolve(Solution solution, Problem problem) throws EvolutionException {

        KnapsackSolution ourSolution = new KnapsackSolution(solution);

        if(!ourSolution.items.isEmpty() && ! ourSolution.getKnapsackProblem().itemsOutside.isEmpty()){
            if(Math.random() >= 0.5){
                return solutionRemoveItem(ourSolution);
            } else return solutionAddItem(ourSolution);

        } else if (!ourSolution.getKnapsackProblem().itemsOutside.isEmpty()) {
            return solutionAddItem(ourSolution);

        } else if (!ourSolution.items.isEmpty()) {
            return solutionRemoveItem(ourSolution);

        } else throw new EvolutionException("No items found during evolution");

    }
}
