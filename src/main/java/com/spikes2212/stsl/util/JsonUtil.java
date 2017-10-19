package com.spikes2212.stsl.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static Map<String, String> fromJson(String json) {
        TypeToken<HashMap<String, String>> typeToken = new HashMapTypeToken();
        return new Gson().fromJson(json, typeToken.getType());
    }

    private static class HashMapTypeToken extends TypeToken<HashMap<String, String>> {
    }
}
