package knapsack;

public class Item {
    double weight;

    double value;
    public Item(double weight, double value){
        if(weight <= 0) throw new IllegalArgumentException();
        this.weight = weight;
        this.value = value;
    }

    public double getWeight(){
        return this.weight;
    }

    public double getValue() {
        return value;
    }

}
