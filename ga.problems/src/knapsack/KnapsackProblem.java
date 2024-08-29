package knapsack;

import ga.framework.model.*;
import ga.framework.operators.*;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem implements Problem {
    private final int capacity;
    private int placeTaken = 0;
    private List<Item> itemsInside = new ArrayList<Item>();
    private List<Item> itemsOutside;

    public KnapsackProblem(int capacity, List<Item> itemsOutside) {
        this.capacity = capacity;
        this.itemsOutside = itemsOutside;
    }

    private boolean canPackItems() throws NoSolutionException {
        for (Item item : itemsOutside){
            if (item.weight + placeTaken <= capacity) return true;
        } throw new NoSolutionException("No items can be added to the knapsack");
    }

    @Override
    public KnapsackSolution createNewSolution() throws NoSolutionException {
        while (canPackItems()) {
            Item item = itemsOutside.get((int) (Math.random() * itemsOutside.size()));
            if(item.weight + placeTaken <= capacity) {
                placeTaken += item.weight;
                itemsOutside.remove(item);
                itemsInside.add(item);
            }
        }
        return new KnapsackSolution(this);
    }
}
