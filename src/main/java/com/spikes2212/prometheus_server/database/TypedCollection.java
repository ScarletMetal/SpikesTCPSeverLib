package com.spikes2212.prometheus_server.database;

import com.mongodb.client.MongoCollection;

public class TypedCollection<T extends Savable> {
    private MongoCollection collection;

    public TypedCollection(MongoCollection collection) {
        this.collection = collection;
    }

}
