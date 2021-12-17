package com.naidiuk.byte_streams;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void run() {
        try (Socket clientSocket = new Socket(InetAddress.getLocalHost(), 1337)) {
            try (DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
                 PrintStream out = new PrintStream(new BufferedOutputStream(clientSocket.getOutputStream()), true);
                 DataInputStream reader = new DataInputStream(new BufferedInputStream(System.in))) {
                String word;
                while (!(word = reader.readLine()).equals("exit")) {
                    out.println(word);
                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
