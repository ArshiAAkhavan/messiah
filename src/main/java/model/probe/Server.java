package model.probe;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private static final int PORT = 8000;
    private HttpServer server;

    public Server(int port){
        try {
            server= HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            System.err.println("failed to bind to the port");
            System.err.println("exiting...");
            System.exit(1);
            e.printStackTrace();
        }
        server.setExecutor(null); // creates a default executor
    }
    public Server(){
        new Server(PORT);
    }

    public void start(){
        server.start();
    }

    public void addContext(String path, HttpHandler httpHandler){
        server.createContext(path, httpHandler);
    }
}
