package com.spikes2212.prometheus_server.database;

import com.mongodb.client.MongoCollection;

public class TypedCollection {
    private MongoCollection collection;

    public TypedCollection(MongoCollection collection) {
        this.collection = collection;
    }

}
