package knapsack;

import ga.framework.model.*;
import ga.framework.operators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class KnapsackProblem implements Problem {
    public final double capacity;
    public double placeTaken = 0;
    public List<Item> itemsOutside =  new ArrayList<>();
    public List<KnapsackSolution> currPop = new ArrayList<>();

//    public KnapsackProblem(KnapsackSolution solution) {
//        this = solution.getProblem();
//
//    }

    public KnapsackProblem(int capacity, List<Item> itemsOutside) {
        this.capacity = capacity;
        for (Item item : itemsOutside) {
            this.itemsOutside.add(item);
        }
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
        List<Item> ourItems = new ArrayList<>(this.itemsOutside);
        KnapsackSolution solution = new KnapsackSolution(this);
        while (canPackItems()) {
            int index  = (int) (Math.random() * ourItems.size());
            Item item = ourItems.get(index);
            if( item != null && item.weight + placeTaken <= capacity) {
                this.placeTaken += item.weight;
                ourItems.remove(index);
                solution.items.add(item);
            }
        }
        this.placeTaken = 0;
        solution.setFitness(0.0);
        currPop.add(solution);
        return solution;
    }

    // 2.3 Version 1

//    public EvolutionaryOperator knapsackMutation  = new EvolutionaryOperator() {
//
//
//
//        private KnapsackSolution solutionRemoveItem(KnapsackSolution ourSolution){
//
//
//            Item item = ourSolution.items.get((int )(Math.random() * ourSolution.items.size()));
//            ourSolution.items.remove(item);
//
//            return ourSolution;
//        }
//
//        private KnapsackSolution solutionAddItem(KnapsackSolution ourSolution) {
//
//            List<Item> itemsPass = ourSolution.getKnapsackProblem().itemsOutside.stream()
//                            .filter(x -> {
//                                return x.weight + ourSolution.getKnapsackProblem().placeTaken <= ourSolution.getKnapsackProblem().capacity;
//                            }).toList();
//            Item item = itemsPass.get((int )(Math.random() * itemsPass.size()));
//            ourSolution.items.add(item);
//            return ourSolution;
//        }
//
//
//
//        @Override
//        public Solution evolve(Solution solution) throws EvolutionException {
//
//            KnapsackSolution ourSolution = new KnapsackSolution(solution);
//
//            if(!ourSolution.items.isEmpty() && !ourSolution.getKnapsackProblem().itemsOutside.isEmpty()){
//                if(Math.random() >= 0.5){
//                    return solutionRemoveItem(ourSolution);
//                } else return solutionAddItem(ourSolution);
//
//            } else if (!ourSolution.getKnapsackProblem().itemsOutside.isEmpty()) {
//                return solutionAddItem(ourSolution);
//
//            } else if (!ourSolution.items.isEmpty()) {
//                return solutionRemoveItem(ourSolution);
//
//            } else throw new EvolutionException("No items found during evolution");
//
//        }
//    };



}
