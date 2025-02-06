package com.pr3;
import java.util.Comparator;

public class SortShirtByDesc implements Comparator<shirt> {
    public int compare(shirt s1, shirt s2){
        return s1.getDescription().compareTo(s2.getDescription());
    }
}
