package com.demo;

import java.io.IOException;
import com.demo.socket.ClientSocket;

public class App {
    private static final String IP_ADDRESS = "192.168.15.11";
    private static final int TCP_PORT = 443;

    public static void main(String[] args) throws IOException {
        ClientSocket clientSocket = new ClientSocket(IP_ADDRESS, TCP_PORT);

        while (true) {
            try {
                clientSocket.connect();
            } catch (IOException e) {
                System.out.println(e);
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
