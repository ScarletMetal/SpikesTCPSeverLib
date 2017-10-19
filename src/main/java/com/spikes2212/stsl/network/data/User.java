package com.spikes2212.stsl.network.data;

import com.spikes2212.stsl.database.Savable;
import org.bson.Document;

public class User implements Savable {
    public Document toDocument() {
        return new Document();
    }

    public void fromDocument(Document object) {

    }
}
