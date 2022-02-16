package trash;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Collections;

public class MyHttpServer {

    HttpServer httpServer;

    MyHttpServer() {
        try {
            // Create HTTP server
            this.httpServer = HttpServer.create(new InetSocketAddress(8000), 0);

//            httpServer.createContext("/", exchange -> {
//                String response = "<h1>¡Bienvenido a mi tarea!</h1>"
//                        + "<h3>Juan David Murillo</h3>"
//                        + "<h3>ID: 2172577</h3>";
//
//                // Text
//                exchange.getResponseHeaders().put("Content-type", Collections.singletonList("text/html"));
//                exchange.sendResponseHeaders(200, response.length());
//                OutputStream outputStream = exchange.getResponseBody();
//                outputStream.write(response.getBytes());
//                outputStream.close();
//            });

            this.httpServer.createContext("/", exchange -> {
                String test = "<h1>Test working!</h1>";

                exchange.getResponseHeaders().put("Content-type", Collections.singletonList("text/html"));
                exchange.sendResponseHeaders(200, test.length());
                OutputStream outputStream = exchange.getResponseBody();
                outputStream.write(test.getBytes());
                outputStream.close();
            });

            this.httpServer.setExecutor(null);
            this.httpServer.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyHttpServer myHttpServer = new MyHttpServer();
    }

    public void get(String path, File file) {
        this.httpServer.createContext(path, exchange -> {
            String test = "<h1>Test working!</h1>";

            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("text/html"));
            exchange.sendResponseHeaders(200, test.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(test.getBytes());
            outputStream.close();
        });
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<h1>¡Bienvenido a mi tarea!</h1>"
                    + "<h3>Juan David Murillo</h3>"
                    + "<h3>ID: 2172577</h3>";

            // Text
//            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("text/html"));
            exchange.sendResponseHeaders(200, response.length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(response.getBytes());
            outputStream.close();
        }
    }
}
