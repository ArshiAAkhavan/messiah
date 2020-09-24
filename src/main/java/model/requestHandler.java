package model;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class requestHandler implements HttpHandler {



    @Override
    public void handle(HttpExchange t) throws IOException {
        System.out.println(t.getClass());
        System.out.println(t.getRequestBody());
        System.out.println(t.getRequestMethod());
        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}
