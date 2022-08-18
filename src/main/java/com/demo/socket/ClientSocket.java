package com.demo.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;

public class ClientSocket {
    private String ipAddress;
    private int tcpPort;

    public ClientSocket(String ipAddress, int tcpPort) {
        this.ipAddress = ipAddress;
        this.tcpPort = tcpPort;
    }

    public void connect() throws IOException {
        try {

            Socket sock = new Socket(ipAddress, tcpPort);

            OutputStream output = sock.getOutputStream();
            PrintWriter printWriter = new PrintWriter(output, true);

            File file = new File("/home/fabio/map.txt");
            FileInputStream fileInputStream = new FileInputStream(file);

            Reader fileInputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader fileBufferReader = new BufferedReader(fileInputStreamReader);

            String line, lines = "";
            while ((line = fileBufferReader.readLine()) != null) {
                lines += line + "\n\n";
            }

            printWriter.println(lines);
            fileBufferReader.close();

            InputStream input = sock.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String response = bufferedReader.readLine();

            if (response == "/exit") {
                sock.close();
            }

        } catch (IOException e) {
            throw new IOException(e);
        }

    }

}
