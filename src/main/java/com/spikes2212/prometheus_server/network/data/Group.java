package com.spikes2212.prometheus_server.network.data;

import com.spikes2212.prometheus_server.database.Savable;
import org.bson.Document;

public class Group implements Savable {
    public Document toDocument() {
        return new Document();
    }

    public void fromDocument(Document object) {

    }
}
