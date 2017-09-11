package com.spikes2212.prometheus_server.database;

import org.bson.Document;

/**
 * An interface that is design to make an object to be parsed from and to {@link Document}
 * @see Document
 * @author Simon "C" Kharmatsky
 */
public interface Savable {

    public String id = "";

    /**
     * A method that returns the data contained in the object parsed to {@link Document}
     * @return {@link Document} object that contains the data of the object
     */
    public Document toDocument();

    /**
     * A method that receives an {@link Document} object and sets the data of the object to the data in the {@link Document}
     * @param object {@link Document} instance that contains data
     */
    public void fromDocument(Document object);
}
