package com.onoprienko.echo;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int clientIndex = 0;
        try (ServerSocket server = new ServerSocket(3000)) {
            System.out.println("Server start work.");
            while (true) {
                Socket socket = server.accept();
                clientIndex++;
                System.out.println("Client " + clientIndex + " connected");
                ServerClient serverClient = new ServerClient(socket, clientIndex);
                serverClient.start();
            }
        }
    }
}
