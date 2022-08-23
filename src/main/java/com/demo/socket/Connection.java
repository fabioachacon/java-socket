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

public class Connection {
    private Socket socket;

    public Connection(String ipAddress, int tcpPort) {
        setConnection(ipAddress, tcpPort);
    }

    public OutputStream getOutput() throws IOException {
        return socket.getOutputStream();
    }

    public InputStream getInput() throws IOException {
        return socket.getInputStream();
    }

    private void setConnection(String ipAddress, int tcpPort) {
        try {
            this.socket = new Socket(ipAddress, tcpPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String file, OutputStream output) throws IOException {
        FileInputStream fileInputStream = createFileInputStream(file);
        BufferedReader fileBufferReader = getBufferStreamReader(fileInputStream);

        printOutputStream(output, fileBufferReader);
        fileBufferReader.close();
    }

    public void readInputBytes(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String response = bufferedReader.readLine();

        System.in.read(response.getBytes());

    }

    private FileInputStream createFileInputStream(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        FileInputStream fileInputStream = new FileInputStream(file);

        return fileInputStream;
    }

    private BufferedReader getBufferStreamReader(FileInputStream fileInputStream) {
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
