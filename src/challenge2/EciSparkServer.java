package challenge2;


import challenge1.HttpServer;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.logging.Handler;

//public class EciSparkServer implements Handler<String> {
public class EciSparkServer {
    private static EciSparkServer _instance = new EciSparkServer();
    private HttpServer httpServer = new HttpServer();

    public static EciSparkServer getInstance() {
        return _instance;
    }

    private Map<String, BiFunction<HttpRequest, HttpResponse, String>> bodyMap = new HashMap();

    public void get(String path, BiFunction<HttpRequest, HttpResponse, String> biFunction) {
        bodyMap.put(path, biFunction);
    }

    public String getValue(String path, HttpRequest req, HttpResponse res) {
        return bodyMap.get(path).apply(req, res);
    }

    public String handle(String path, HttpRequest req, HttpResponse res) {
        return getValue(path, req, res);
    }
}
