package com.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

import com.demo.socket.Connection;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("IP: ");
        String ipAddress = scanner.nextLine();

        System.out.print("Port: ");
        Integer tcpPort = scanner.nextInt();

        scanner.close();

        Connection connection = new Connection(ipAddress, tcpPort);

        while (true) {
            try {
                OutputStream output = connection.getOutput();

                connection.sendFile("/home/fabio/map.txt", output);

            } catch (IOException e) {
                System.out.println(e);
            }

            sleep(3000);

        }

    }

    private static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
