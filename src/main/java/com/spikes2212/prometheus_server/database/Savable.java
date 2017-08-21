package com.spikes2212.prometheus_server.database;

import org.bson.Document;

public interface Savable {
    
    public Document toDocument();

    public void fromDocument(Document object);
}
