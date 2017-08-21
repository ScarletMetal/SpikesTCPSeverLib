package com.spikes2212.prometheus_server;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.spikes2212.prometheus_server.util.LogUtil;


public class Main {
    private static MongoDatabase roomsDB;
    private static MongoDatabase usersDB;

    private static void processArguments(String[] args) {
        LogUtil.disable();

        if (args.length > 0 && args[0].equals("--log")) {
            LogUtil.enable();
        }
    }

    private static void mongoInit() {
        MongoClient client = new MongoClient(Constants.MONGODB.HOST, Constants.MONGODB.PORT);

        roomsDB = client.getDatabase(Constants.MONGODB.ROOMS_DB_NAME);
        LogUtil.data("Loading rooms db", "success");
        usersDB = client.getDatabase(Constants.MONGODB.USERS_DB_NAME);
        LogUtil.data("loading users db", "success");
    }

    public static void main(String[] args) {
        processArguments(args);
        mongoInit();
    }
}
