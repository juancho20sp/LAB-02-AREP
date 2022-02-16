package main.challenge1.ex3;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket("127.0.0.1", 1234);

            System.out.println("Socket ready for sending data...");

            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String clientInput;

        try {
            while ((clientInput = stdIn.readLine()) != null) {
                out.println(clientInput);
                System.out.println(in.readLine());
            }

            out.close();
            in.close();
            stdIn.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
