package client;

import interfaces.Task;

public class DeleteToyPrice implements Task {

    //Define variable
    private final String toyName;

    public DeleteToyPrice(String toyName) {
        this.toyName = toyName;
    } //Define  constructor

    public Object execute() { 
        return deleteToyPrice(toyName);
    }
    public Object deleteToyPrice(String toyName) { 
        AddToyPrice.getToyPriceTable().remove(toyName);
        return "\n" + AddToyPrice.getToyPriceTable();
    }
}
