package client;

import interfaces.Task;

public class CalculateToyCost implements Task {

    private final String toyName;
    private final int toyQuantity;

    public double toyCost;
    public double toyDiscount;

    public CalculateToyCost(String toyName, int toyQuantity) {
        this.toyName = toyName;
        this.toyQuantity = toyQuantity;
    }

    public Object execute() {
        return calculateToyCost(toyName, toyQuantity);
    } 

    public Object calculateToyCost(String toyName, int toyQuantity) { 
        int totalCost = AddToyPrice.getToyPriceTable().get(toyName);
        toyCost = totalCost * toyQuantity;
        toyDiscount = toyCost * 0.95;
        return "\n" + "Your total cost for the toy is: " + toyCost +"\nYour discounted cost is: " + toyDiscount;
    }
}
