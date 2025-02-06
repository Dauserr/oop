package com.pr3;
import java.util.Comparator;

public class SortShirtByCount implements Comparator<shirt> {//comparator для сортинга
    @Override //overriding other method
    public int compare(shirt s1,shirt s2){
        return Integer.compare(s1.getCount(), s2.getCount());
    }
}