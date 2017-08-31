package com.spikes2212.prometheus_server.network;

import com.spikes2212.prometheus_server.network.message.Message;
import com.spikes2212.prometheus_server.network.message.TestMessage;
import com.spikes2212.prometheus_server.network.message.UnknownMessageTypeException;
import com.spikes2212.prometheus_server.util.JsonUtil;
import com.spikes2212.prometheus_server.util.LogUtil;

import javax.lang.model.type.UnknownTypeException;
import java.io.IOException;

public class ListeningRunnable implements Runnable {

    private Connection connection;
    public ListeningRunnable(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            String data;
            while ((data = connection.readLine()) != null) {
                Message msg = JsonUtil.fromJson(data, Message.class);

            }
        } catch (IOException ioe) {
            LogUtil.error("IOE while running main listening loop", "");
            ioe.printStackTrace();
        }
    }
    private void processMessage(Object msg) throws UnknownMessageTypeException {
        if (!(msg instanceof Message)) {
            throw new UnknownMessageTypeException(msg);
        }

        Message message = (Message) msg;

        if (msg instanceof TestMessage) {
            processTestMessage((TestMessage)message);
        }
    }

    private void processTestMessage(TestMessage message) {
        System.out.println(message.myName);
    }

}
