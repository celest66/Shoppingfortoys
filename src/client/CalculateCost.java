package client;

import interfaces.Task;

public class CalculateCost implements Task {


    private final int toyQuantity, amountPaid;

    private final String toyName;

    private final String cashier;
    public CalculateCost(String toyName, String cashier, int toyQuantity, int amountPaid) {
        this.toyName = toyName;
        this.toyQuantity = toyQuantity;
        this.amountPaid = amountPaid;
        this.cashier = cashier;
    }
    public Object execute() {
        return getCost(toyName, cashier, toyQuantity, amountPaid);
    } 
    public Object getCost(String toyName, String cashier, int toyQuantity, int amountPaid) { //Method to implement the
                                                                                            // toy cost calculation and
                                                                                            //print a receipt...
        int price = AddToyPrice.getToyPriceTable().get(toyName);
        double cost = price * toyQuantity;
        double discount = cost * 0.95;
        double balance = amountPaid - discount;
        return "\n" + "Total Cost: " + cost + "\nDiscounted Cost: " + discount + "\nAmount Paid: " + amountPaid + "\nBalance Due: " + balance + "\nCashier: " + cashier;
    }
}
