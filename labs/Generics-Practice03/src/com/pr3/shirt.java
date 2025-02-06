package com.pr3;
import java.util.Comparator;

public class shirt {
    private String id;
    private String description;
    private String color;
    private String size;
    private int count;

    public shirt(String id, String description, String color, String size, int count) {
        this.id = id;
        this.description = description;
        this.color = color;
        this.size = size;
        this.count = count;
    }
    public String getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public String getColor() {
        return color;
    }
    public String getSize() {
        return size;
    }
    public int getCount() {
        return count;
    }
    public void addItems(int count) {
        this.count += count;
    }
    public void removeItems(int count) {
        this.count -= count;
    }
    public String toString() {
        return "Shirt ID: " + id + "\nDescription: " + description + "\nColor: " + color + "\nSize: " + size + "\nInventory: " + count +"\n";
    }
}
