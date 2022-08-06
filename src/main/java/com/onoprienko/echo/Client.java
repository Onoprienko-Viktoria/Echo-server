package com.onoprienko.echo;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 3000);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            System.out.println("Client started. If you write 'stop' client will close.");
            while (true) {
                System.out.println("Input your message: ");
                String message = consoleReader.readLine();
                if (Objects.equals(message, "stop")) {
                    bufferedWriter.write(message + "\n");
                    bufferedWriter.flush();
                    break;
                }
                bufferedWriter.write(message + "\n");
                bufferedWriter.flush();
                String echoMassage = bufferedReader.readLine();
                System.out.println(echoMassage);
            }
            System.out.println("Client closed");
        }
    }
}