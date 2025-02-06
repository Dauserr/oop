package com.generics;

import java.util.*;

public class parenmatcher {

    private Deque<Character> stack = new ArrayDeque<>();

    public boolean processLine(String line) {
        stack.clear(); // для того что бы некст лайн использовал пустой стэк

        char[] curLine = line.toCharArray();

        for(char c : curLine) {
            switch (c) {//свитч смотрит что из кейсов правильно и пикает его
                case '(':
                    stack.push(c);
                    break;
                case ')':{//каждый раз опустошает стэк и если он пустой но он нашел ) значит инвалид
                    if(stack.isEmpty()){
                        return false;
                    } else {
                        stack.pop();
                    }
                    break;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] lines = {
                "(a + b) * (c + d)",         // Valid
                "((a + b) * (c + d)",        // Invalid
                "(a + b) * (c + d))",        // Invalid
                "((a + b) * ((c + d)))",     // Valid
        };

        parenmatcher pmatcher = new parenmatcher();
        for(int i = 0; i < lines.length; i++) {
            boolean found = pmatcher.processLine(lines[i]);
            if (found) {
                System.out.println("Line " + i + " is valid");
            } else {
                System.out.println("Line " + i + " is not valid");
            }

        }
    }
}
