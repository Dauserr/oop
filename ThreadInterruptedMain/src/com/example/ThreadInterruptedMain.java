package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThreadInterruptedMain {

    public static void main(String[] args) {
        Runnable r = new Counter();
        Thread t = new Thread(r);
        t.start();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Press Enter to stop the counter...");
            br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Thread is alive: " + t.isAlive());
        t.interrupt();

        try {
            Thread.sleep(1000); // даём потоку время завершиться
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread is alive: " + t.isAlive());
    }
}
