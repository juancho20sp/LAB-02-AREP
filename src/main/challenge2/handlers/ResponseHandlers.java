package main.challenge2.handlers;

import main.challenge1.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;

public class ResponseHandlers {
    static String filesPath = "src/main/";

    public static String getDefaultResponse() {
        String res = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title of the document</title>\n"
                + "</head>"
                + "<body>"
                + "<h2>This is the default server response :)</h2>"
                + "</body>"
                + "</html>";

        return res;
    }

    public static String getNotFoundResponse(String message) {
        String res = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Resource not found</title>\n"
                + "</head>"
                + "<body>"
                + "<h2>Oh no, something went wrong...</h2>"
                + "<h3>A repetir AREP papá</h3>"
                + "<p>" + message + "</p>"
                + "</body>"
                + "</html>";

        return res;
    }

    private static boolean isBinary(String extension) {
        return extension.equals("jpg") || extension.equals("png");
    }

    private static String prepareImage(URI uri, OutputStream outputStream) {
        String response;
        String extension = uri.toString().substring(uri.getPath().lastIndexOf(".") + 1);
//        String filesPath = "src/main/public";

        // TODO
        // $
        System.out.println("   ");
        System.out.println("   ");
        System.out.println("   ");
        System.out.println("Response handlers: ");
        System.out.println(HttpServer.types.get(extension.toUpperCase()));
        String contentType = "application/json";

        response = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + HttpServer.types.get(contentType.toUpperCase()) + "\r\n"
                + "\r\n";

        File file = new File(filesPath + uri.getPath());

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            ImageIO.write(bufferedImage, extension, byteArrayOutputStream);
            dataOutputStream.writeBytes(response);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public static String prepareResponse(URI uri, OutputStream outputStream) {
        String response;

        // TODO
        // $
        String extension = uri.toString();
        System.out.println("URI (responseHandlers): " + extension);
        System.out.println("   ");
        System.out.println("   ");
        System.out.println("   ");

        if (isBinary(extension)) {
            return prepareImage(uri, outputStream);
        }

        response = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + HttpServer.types.get(extension.toUpperCase()) + "\r\n"
                + "\r\n";

        File file = new File(filesPath + uri.getPath());

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String fileLine;

            while ((fileLine = bufferedReader.readLine()) != null) {
                response += fileLine;
            }

        } catch (FileNotFoundException e) {
            return getNotFoundResponse(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
