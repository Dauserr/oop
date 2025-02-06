package com.pr3;
import java.util.*;

public class TestItemCounter {
    public static void main(String[] args) {
        Map<String,shirt> polos = new HashMap<>();
        polos.put("P001", new shirt("P001", "Blue Polo Shirt", "Blue", "L", 24));
        polos.put("P002", new shirt("P002", "Black Polo Shirt", "Black", "M", 15));
        polos.put("P003", new shirt("P003", "Maroon Polo Shirt", "Maroon", "XL", 20));
        polos.put("P004", new shirt("P004", "Tan Polo Shirt", "Tan", "S", 19));

        List<DukeTransaction> transactions = Arrays.asList(
                new DukeTransaction("P001", "Purchase", 5),
                new DukeTransaction("P002", "Sale", 3),
                new DukeTransaction("P003", "Purchase", 10),
                new DukeTransaction("P004", "Sale", 5),
                new DukeTransaction("P001", "Sale", 10)
        );

        for (DukeTransaction t : transactions) {
            if (polos.containsKey(t.getProductId())) {
                shirt curshirt = polos.get(t.getProductId());
                switch(t.getTransactionType()){
                    case "Purchase":
                        curshirt.addItems(t.getCount());
                        break;
                    case "Sale":
                        curshirt.removeItems(t.getCount());
                        break;
                    default:
                        System.out.println("Error: Invalid transaction type");
                }
            } else {
                System.out.println("Error: Invalid part number");
            }
        }
        List<shirt> poloList = new ArrayList<>(polos.values());

        Comparator<shirt> sortDescription = new SortShirtByDesc();
        Comparator<shirt> sortCount = new SortShirtByCount();

        Collections.sort(poloList,sortDescription);
        System.out.println("=== Inventory Report - Description ===");
        for (shirt s : poloList) {
            System.out.println(s);
        }
        Collections.sort(poloList,sortCount);
        System.out.println("=== Inventory Report - Count ===");
        for (shirt s : poloList) {
            System.out.println(s);
        }


    }
}
