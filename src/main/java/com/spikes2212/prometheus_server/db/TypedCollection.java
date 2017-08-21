package com.spikes2212.prometheus_server.db;

import com.mongodb.DBCollection;

public class TypedCollection<T extends Savable> {

    private DBCollection collection;

    public TypedCollection(DBCollection collection) {
        this.collection = collection;
    }

    public void insert(T object) {
        collection.insert(object.toDocument());
    }
}
