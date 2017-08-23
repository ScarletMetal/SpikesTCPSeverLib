package com.spikes2212.prometheus_server.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {
    private Socket socket;
    private DataOutputStream output;
    private BufferedReader input;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.input  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new DataOutputStream(socket.getOutputStream());
    }
}
