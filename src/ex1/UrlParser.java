package ex1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UrlParser {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.google.com:80/index.html?size=10#arep-es-lo-mejor");
            System.out.println("Protocol: " + url.getProtocol());
            System.out.println("Authority: " + url.getAuthority());
            System.out.println("Host: " + url.getHost());
            System.out.println("Port: " + url.getPort());
            System.out.println("Path: " + url.getPath());
            System.out.println("Query: " + url.getQuery());
            System.out.println("File: " + url.getFile());
            System.out.println("Ref: " + url.getRef());

        } catch (MalformedURLException e) {
            Logger.getLogger(UrlParser.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
}
