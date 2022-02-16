package main.challenge2;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.BiFunction;

public class EciSpark {
    public static void get(String path, BiFunction<HttpRequest, HttpResponse, String> biFunction) {
        EciSparkServer eciSparkServer = EciSparkServer.getInstance();
        eciSparkServer.get(path, biFunction);
    }

    public static void startServer() {
        EciSparkServer eciSparkServer = EciSparkServer.getInstance();
        eciSparkServer.startServer();
    }
}
