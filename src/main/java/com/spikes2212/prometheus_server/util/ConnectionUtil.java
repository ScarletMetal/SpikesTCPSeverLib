package com.spikes2212.prometheus_server.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionUtil {

    private static boolean isURLReachable(String urlString) throws IOException {

        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int respCode = connection.getResponseCode();
        return respCode == 200;
    }

    public static boolean isNetworkReachable() throws IOException {
        if (isURLReachable("https://google.com")) return true;
        if (isURLReachable("https://spikes2212.com")) return true;
        if (isURLReachable("https://archlinux.org")) return true;
        return false;
    }

}
