package com.example.client;

import java.util.*;
import java.util.concurrent.*;

public class NetworkClientMain {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Map<RequestResponse, Future<RequestResponse>> callables = new HashMap<>();

        String host = "localhost";
        for (int port = 10000; port < 10010; port++) {
            RequestResponse request = new RequestResponse(host, port);
            NetworkClientCallable task = new NetworkClientCallable(request);
            Future<RequestResponse> future = executor.submit(task);
            callables.put(request, future);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted while waiting for termination");
        }

        for (Map.Entry<RequestResponse, Future<RequestResponse>> entry : callables.entrySet()) {
            RequestResponse req = entry.getKey();
            try {
                RequestResponse response = entry.getValue().get();
                System.out.println(req.host + ":" + req.port + " " + response.response);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error communicating with server " + req.host + ":" + req.port);
            }
        }
    }
}
