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

    public Document findOne(Document query) {
        FindIterable<Document> iterable = collection.find();
        return iterable.first();
    }

    public void updateOne(Document query, T object) {
        collection.updateOne(query, object.toDocument());
    }


}
