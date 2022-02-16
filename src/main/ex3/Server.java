package main.challenge1.ex3;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        final int PORT = 1234;

        try {
            serverSocket = new ServerSocket(PORT);

            System.out.println("Server listening on port: " + PORT);
            System.out.println("Waiting for connections...");

            clientSocket = serverSocket.accept();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje: " + inputLine);

                try {
                    double number = Double.parseDouble(inputLine);
                    outputLine = "Respuesta: " + number * number;
                } catch (NumberFormatException ex) {
                    outputLine = "Respuesta: " + inputLine;
                }

                out.println(outputLine);

                if (outputLine.equals("Respuesta: Bye.")) {
                    break;
                }
            }

            out.close();
            in.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
