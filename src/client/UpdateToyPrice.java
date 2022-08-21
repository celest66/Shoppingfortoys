package client;

import interfaces.Task;

public class UpdateToyPrice implements Task {
    private final String toyName;
    private final Integer newToyPrice;

    public UpdateToyPrice(String toyName, Integer newToyPrice) {
        this.toyName = toyName;
        this.newToyPrice = newToyPrice;
    }
    public Object execute() { 
        return updateToyPrice(toyName, newToyPrice);
    }
    public Object updateToyPrice(String toyName, int newToyPrice) { 
        AddToyPrice.getToyPriceTable().put(toyName, newToyPrice);
        return "\n" + AddToyPrice.getToyPriceTable();
    }
}
