package com.spikes2212.prometheus_server.network.message;

public class TestMessage extends Message {
    public String myName;
    public TestMessage() {
        this.stringedClass = ClientMessageTypeNames.TEST_MESSAGE;
    }

    public TestMessage(String name) {
        this();
        myName = "123";
    }
}
