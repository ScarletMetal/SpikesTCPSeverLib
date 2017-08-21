package com.spikes2212.prometheus_server.database;

import com.mongodb.BasicDBObject;

public interface Savable {
    
    public BasicDBObject toDocument();

    public BasicDBObject fromDocument(BasicDBObject object);
}
