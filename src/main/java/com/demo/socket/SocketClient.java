package com.demo.socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.stream.Stream;

public class SocketClient {
    private String ipAddress;
    private int tcpPort;

    public SocketClient(String ipAddress, int tcpPort) {
        this.ipAddress = ipAddress;
        this.tcpPort = tcpPort;
    }

    public void connect() throws IOException {
        try {
            Socket sock = new Socket(ipAddress, tcpPort);
            OutputStream outputStream = sock.getOutputStream();

            FileInputStream fileInputStream = createFileInputStream("/home/fabio/map.txt");
            BufferedReader fileBufferReader = getBufferStreamReader(fileInputStream);

            printOutputStream(outputStream, fileBufferReader);
            fileBufferReader.close();

            InputStream inputStream = sock.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String response = bufferedReader.readLine();

            if (response == "/exit") {
                sock.close();
            }

        } catch (IOException e) {
            throw new IOException(e);
        }

    }

    private FileInputStream createFileInputStream(String fileName) throws FileNotFoundException {
        try {
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);

            return fileInputStream;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        }

    }

    public BufferedReader getBufferStreamReader(FileInputStream fileInputStream) {
        Reader fileInputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader fileBufferReader = new BufferedReader(fileInputStreamReader);

        return fileBufferReader;
    }

    private void printOutputStream(OutputStream outputStream, BufferedReader bufferedReader) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream, true);

        Stream<String> streamOfStrings = bufferedReader.lines();
        streamOfStrings.forEach(line -> {
            if (line != null && !line.isEmpty()) {
                printWriter.println(line + "\n");
            }
        });

    }

}
