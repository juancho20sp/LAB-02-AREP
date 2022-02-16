package trash;

//import ex2.Browser;

import java.net.*;
import java.io.*;

public class HttpServerTest {
    public static void main(String[] args) throws IOException {
        final int PORT = 1234;

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);

        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        while (true) {
            try {
                System.out.println("Listo para recibir ...");
                Socket clientSocket = serverSocket.accept();

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine, outputLine;

                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    if (inputLine.isEmpty()) {
                        break;
                    }
                }

                // Text
                outputLine = "HTTP/1.1 200 OK\r\n"
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
                        + "<img src='../images/test.jpg'/>"
                        + "</body>"
                        + "</html>" + inputLine;

                out.println(outputLine);

                // Images
//                File file = new File("../images/test.jpg");
//                OutputStream outputStream = clientSocket.getOutputStream();

//                Files.copy(file.toPath(), outputStream);
//                outputStream.close();


                out.close();
                in.close();
                clientSocket.close();


            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

        }



//        serverSocket.close();
    }
}
