package com.naidiuk.char_streams;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void start() {
        try (ServerSocket server = new ServerSocket(1337);
             Socket clientSocket = server.accept()) {
            System.out.println("Hooray! Server is running!");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                String word;
                while ((word = in.readLine()) != null) {
                    System.out.printf("${%s}\n", word);
                    out.write(String.format("echo: ${%s}\n", word));
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
