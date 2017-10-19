package com.spikes2212.stsl.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private DataOutputStream output;
    private BufferedReader input;
    private Thread listeningThread;
    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    public void writeData(String data) throws IOException {
        output.write((data+"\n").getBytes());
    }

    public String readLine() throws IOException {
        return input.readLine();
    }
}
