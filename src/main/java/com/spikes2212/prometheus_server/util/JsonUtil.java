package com.spikes2212.prometheus_server.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    /*
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }
    */

    public static Map<String, String> fromJson(String json) {
        return new Gson().fromJson(json, new TypeToken<HashMap<String, String>>() {}.getType());
    }
}
