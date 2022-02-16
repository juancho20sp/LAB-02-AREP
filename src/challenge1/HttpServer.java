package challenge1;

import challenge1.utils.DataTypes;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Juan David Murillo
 * @version 1.0
 */
public class HttpServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public final static Map<String, String> types = new HashMap<String, String>();

    // $ -> delete
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();
    }

    public HttpServer() {
        generateTypesMap();
        startServer();
    }

    private void generateTypesMap() {
        for (DataTypes type : DataTypes.values()) {
            types.put(type.toString(), type.value);
        }
    }

    public void startServer() {
        int port = this.getPort();



        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port: " + port);

            while (true) {
                // Client
                this.clientSocket = this.serverSocket.accept();
                System.out.println("Connection established");

                // Stream
                OutputStream outputStream = clientSocket.getOutputStream();

                this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

                String input, output;
                ArrayList<String> request = new ArrayList<>();

                while ((input = in.readLine()) != null) {
                    System.out.println("Received: " + input);
                    request.add(input);

                    if (input.isEmpty()) {
                        break;
                    }
                }

                // TODO -> handler
                output = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>"
                        + "<html>"
                        + "<head>"
                        + "<meta charset=\"UTF-8\">"
                        + "<title>Title of the document</title>\n"
                        + "</head>"
                        + "<body>"
                        + "My Web Site"
                        + "</body>"
                        + "</html>" + input;

                out.println(output);
                out.close();
                in.close();
                clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        int port;
        if (System.getenv("PORT") != null){
            port = Integer.parseInt(System.getenv("PORT"));
        } else {
            port = 1234;
        }

        return port;
    }
}
