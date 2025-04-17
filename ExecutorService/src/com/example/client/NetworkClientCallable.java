package com.example.client;

import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class NetworkClientCallable implements Callable<RequestResponse> {

    private final RequestResponse request;

    public NetworkClientCallable(RequestResponse request) {
        this.request = request;
    }

    @Override
    public RequestResponse call() {
        try (Socket socket = new Socket(request.host, request.port);
             Scanner scanner = new Scanner(socket.getInputStream())) {
            request.response = scanner.next();
        } catch (IOException | NoSuchElementException e) {
            request.response = "Error";
        }
        return request;
    }
}
