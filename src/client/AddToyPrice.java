package client;

import interfaces.Task;

import java.util.HashMap;

public class AddToyPrice implements Task {
    private final String toyName;
    private final Integer toyPrice;

    private static final HashMap<String, Integer> toyPriceTable = new HashMap<String, Integer>(); //Create a Hash Map that will act as our table...

    public static HashMap<String, Integer> getToyPriceTable() { 
        toyPriceTable.put("Buzz", 750);
        toyPriceTable.put("Teddy", 1000);
        toyPriceTable.put("Robot", 1500);
        toyPriceTable.put("Trucker", 290);
        toyPriceTable.put("Dolly", 350);
        toyPriceTable.put("Football", 450);
        toyPriceTable.put("Guitar", 500);
        return toyPriceTable;
    }
    public AddToyPrice(String toyName, Integer toyPrice) {
        this.toyName = toyName;
        this.toyPrice = toyPrice;
    }

    public Object execute() {
        return addToyPrice(toyName, toyPrice);
    } 

    public Object addToyPrice(String toyName, Integer toyPrice) { 
        toyPriceTable.put(toyName, toyPrice);
        return "\n" + getToyPriceTable();
    }

}
