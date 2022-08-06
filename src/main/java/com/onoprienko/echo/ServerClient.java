package com.onoprienko.echo;

import java.io.*;
import java.net.Socket;

public class ServerClient extends Thread {
    Socket socket;
    int index;

    ServerClient(Socket client, int clientIndex) {
        this.socket = client;
        index = clientIndex;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            while (true) {
                String message = bufferedReader.readLine();
                if (message.equals("stop")) {
                    System.out.println("Client " + index + " leave Server");
                    break;
                }
                bufferedWriter.write("Echo: " + message + "\n");
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}