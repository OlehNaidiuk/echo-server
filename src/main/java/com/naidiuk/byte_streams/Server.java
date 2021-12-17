package com.naidiuk.byte_streams;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() {
        try(ServerSocket server = new ServerSocket(1337);
            Socket clientSocket = server.accept()){
            System.out.println("Hooray! Server is running!");
            try(DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                PrintStream out = new PrintStream(new BufferedOutputStream(clientSocket.getOutputStream()),true)) {
                String word;
                while ((word = in.readLine()) != null) {
                    System.out.printf("${%s}\n", word);
                    out.printf("echo: ${%s}\n", word);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
