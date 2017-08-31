package com.spikes2212.prometheus_server.network.message;

import javax.lang.model.type.UnknownTypeException;

public class UnknownMessageTypeException extends Throwable {
    private Object msg;

    public UnknownMessageTypeException(Object msg) {
        this.msg = msg;
    }

    public Class getMessageType() {
        return msg.getClass();
    }


}
