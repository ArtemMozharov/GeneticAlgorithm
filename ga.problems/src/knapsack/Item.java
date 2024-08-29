package knapsack;

public class Item {
    int weight;
    int value;
    public Item(int weight, int value){
        this.weight = weight;
        this.value = value;
    }
    float fitness = (float) value / weight;

}
