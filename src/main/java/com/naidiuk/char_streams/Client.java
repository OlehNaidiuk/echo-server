package com.naidiuk.char_streams;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public void start() {
        try (Socket clientSocket = new Socket(InetAddress.getLocalHost(), 1337)) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                String word;
                while (!(word = reader.readLine()).equals("exit")) {
                    out.write(word + "\n");
                    out.flush();
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
        client.start();
    }
}