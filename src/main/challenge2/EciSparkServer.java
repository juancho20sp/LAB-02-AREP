package main.challenge2;

import main.challenge1.HttpServer;
import main.challenge2.handlers.ResponseHandlers;
//
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * @author Juan David Murillo
 * @version 1.0
 */
public class EciSparkServer {
    private static EciSparkServer _instance;
    private HttpServer httpServer = new HttpServer();

    public static void main(String[] args) {
        EciSparkServer eciSparkServer = EciSparkServer.getInstance();
        eciSparkServer.startServer();
    }

    public static EciSparkServer getInstance() {
        if (_instance == null) {
            _instance = new EciSparkServer();
        }
        return _instance;
    }

    public void startServer() {
        httpServer.startServer();
    }

    public static String get(ArrayList<String> request, OutputStream outputStream) {
        try {
            String contentType = request.get(0).split(" ")[1];

            // $
            System.out.println("   ");
            System.out.println("   ");
            System.out.println("   ");
            System.out.println("ContenType (EciSParkServer): " + contentType);

            URI uri = new URI(contentType);

            if (uri.getPath().startsWith("/public")) {
                return ResponseHandlers.prepareResponse(uri, outputStream);
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseHandlers.getDefaultResponse();
    }

    public static void get(String path, BiFunction<HttpRequest, HttpResponse, String> biFunction) {

    }



//    private Map<String, BiFunction<HttpRequest, HttpResponse, String>> bodyMap = new HashMap();
//
//    public void get(String path, BiFunction<HttpRequest, HttpResponse, String> biFunction) {
//        bodyMap.put(path, biFunction);
//    }
//
//    public String getValue(String path, HttpRequest req, HttpResponse res) {
//        return bodyMap.get(path).apply(req, res);
//    }
//
//    public String handle(String path, HttpRequest req, HttpResponse res) {
//        return getValue(path, req, res);
//    }
}
