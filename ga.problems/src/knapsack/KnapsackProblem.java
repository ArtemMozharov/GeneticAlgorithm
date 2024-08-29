package knapsack;

import ga.framework.model.*;
import ga.framework.operators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class KnapsackProblem implements Problem {
    private final double capacity;
    private double placeTaken = 0;
    private List<Item> itemsOutside;

    public KnapsackProblem(int capacity, List<Item> itemsOutside) {
        this.capacity = capacity;
        this.itemsOutside = itemsOutside;
    }

    private boolean canPackItems() throws NoSolutionException {
        for (Item item : itemsOutside){
            if (item.weight + placeTaken <= capacity){
                return true;
            }
        }
        return false;
    }

    @Override
    public KnapsackSolution createNewSolution() throws NoSolutionException {
        KnapsackSolution solution = new KnapsackSolution(this);
        while (canPackItems()) {
            Item item = itemsOutside.get((int) (Math.random() * itemsOutside.size()));
            if(item.weight + placeTaken <= capacity) {
                placeTaken += item.weight;
                //itemsOutside.remove(item);
                solution.items.add(item);
            }
        }
        solution.setFitness(0.0);
        return solution;
    }

    // 2.3

//    EvolutionaryOperator knapsackMutation  = new EvolutionaryOperator() {
//
//
//
//        private KnapsackSolution solutionHeads(Solution solution){
//            KnapsackSolution ourSolution = new KnapsackSolution(solution);
//            KnapsackProblem problem = ourSolution.getProblem();
//            Item item = problem.itemsInside.get((int )(Math.random() * itemsOutside.size()));
//            problem.itemsInside.remove(item);
//            problem.placeTaken = placeTaken - item.weight;
//            return new KnapsackSolution(problem);
//        }
//
//        private KnapsackSolution solutionTails(Solution solution){
//            KnapsackSolution ourSolution = new KnapsackSolution(solution);
//            KnapsackProblem problem = ourSolution.getProblem();
//            List<Item> itemsPass = problem.itemsOutside.stream()
//                            .filter(x -> {
//                                return x.weight + problem.placeTaken <= problem.capacity;
//                            }).toList();
//            Item item = itemsPass.get((int )(Math.random() * itemsPass.size()));
//            problem.itemsInside.add(item);
//            problem.placeTaken += item.weight;
//            problem.itemsOutside.remove(item);
//            return new KnapsackSolution(problem);
//        }
//
//
//
//        @Override
//        public Solution evolve(Solution solution) throws EvolutionException {
//            if(Math.random() >= 0.5){
//                return solutionHeads(solution);
//            } else return solutionTails(solution);
//        }
//    };


}
