package com.example;

public class Counter implements Runnable {

    @Override
    public void run() {
        int x = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("The current value of x is: " + x++);
            try {
                Thread.sleep(1000); // задержка 1 секунда
            } catch (InterruptedException e) {
                // Поток был прерван во время сна
                return;
            }
        }
    }
}
