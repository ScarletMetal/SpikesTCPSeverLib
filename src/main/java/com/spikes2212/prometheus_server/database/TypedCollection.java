package com.spikes2212.prometheus_server.database;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public class TypedCollection<T extends Savable> {
    private MongoCollection<Document> collection;

    public TypedCollection(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    public void insertOne(T object) {
        collection.insertOne(object.toDocument());
    }
    public void insertMany(List<T> objects) {
        for (T object : objects) {
            insertOne(object);
        }
    }
    public Document findOne(Document query) {
        FindIterable<Document> iterable = collection.find();
        return iterable.first();
    }


}
