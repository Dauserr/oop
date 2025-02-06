package com.product;
import java.util.*;

public class ProductCounter {
    private Map<String, Long> partCountMap = new HashMap<>();// hash map to count and group

    private Map<String, String> descriptionMap = new TreeMap<>(); // tree mmap to sort in alphabeical order

    public ProductCounter(Map descriptionMap) {
        this.descriptionMap = descriptionMap;//this. отмечает мап созданный сверху
    }

    public void processList(String[] list){
        long curVal=0;
        for(String s : list){
            if(partCountMap.containsKey(s)){
                curVal = partCountMap.get(s);
                curVal++;
                partCountMap.put(s, curVal);
            } else {
                partCountMap.put(s, 1L);
            }
        }
    }

    public void printReport(){
        System.out.println("=== Product Report ===");
        for(String s : descriptionMap.keySet()){
            System.out.println("Name: " + descriptionMap.get(s) + "\tCount: " + partCountMap.get(s));
        }
    }

    public static void main(String[] args) {
        Map descriptionMap = new TreeMap<>();
        descriptionMap.put("1S01", "Blue Polo Shirt");
        descriptionMap.put("1S02", "Black Polo Shirt");
        descriptionMap.put("1H01", "Red Ball Cap");
        descriptionMap.put("1M02", "Duke Mug");

        String[] parts = {"1S01", "1S01", "1S02", "1M02", "1M02", "1H01", "1S01", "1S02", "1S02", "1H01", "1S01"};
        ProductCounter pc = new ProductCounter(descriptionMap);
        pc.processList(parts);
        pc.printReport();
    }

}
