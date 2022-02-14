package ex2;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Browser {
    String url;


    public static void main(String[] args) {
        Browser browser = new Browser();
    }

    Browser() {
        this.url = this.askForUrl();
        String html = this.getHtmlFromUrl();
        this.writeData(html);
        System.out.println(this.url);
        System.out.println(" --- HTML ---");
        System.out.println(html);
    }

    public Browser(String URL) {
        this.url = URL;
        String html = this.getHtmlFromUrl();
        this.writeData(html);
        System.out.println(this.url);
        System.out.println(" --- HTML ---");
        System.out.println(html);
    }

    private String askForUrl() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una URL: ");
        String res = scanner.nextLine();
        scanner.close();

        return res;
    }

    private String getHtmlFromUrl() {
        String htmlResponse = "";

        try {
            // Create the URL
            URL siteURL = new URL(this.url);

            // Create the connection
            URLConnection urlConnection = siteURL.openConnection();

            // Get headers
            Map<String, List<String>> headers = urlConnection.getHeaderFields();

            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();

            // Print headers
            for(Map.Entry<String, List<String>> entry : entrySet){
                String headerName = entry.getKey();

                if (headerName != null) {
                    System.out.println(headerName + ": ");
                }

                List<String> headerValues = entry.getValue();

                for(String value: headerValues) {
                    System.out.println(value);
                }

                System.out.println(" ");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(siteURL.openStream()));

            String inputLine = null;

            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
                htmlResponse += inputLine;
            }

        } catch (MalformedURLException ex) {
            System.out.println("URL mal formada");
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            System.out.println("Error en la conexión");
            e.printStackTrace();
        }

        return htmlResponse;
    }

    private void writeData(String html) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("resultado.html"));

            writer.write(html);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
