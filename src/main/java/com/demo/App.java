package com.demo;

import java.io.IOException;
import com.demo.socket.SocketClient;

public class App {
    private static final String IP_ADDRESS = "192.168.15.11";
    private static final int TCP_PORT = 443;

    public static void main(String[] args) throws IOException {
        SocketClient socketClient = new SocketClient(IP_ADDRESS, TCP_PORT);

        while (true) {
            try {
                socketClient.connect();
            } catch (IOException e) {
                System.out.println(e);
            }

            sleep(3000);

        }

    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
