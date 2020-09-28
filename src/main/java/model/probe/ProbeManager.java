package model.probe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;

public class ProbeManager {

    private ArrayList<Receiver> receivers = new ArrayList<>();

    @JsonIgnore
    Server server;

    public ProbeManager(int port) {
        server = new Server(port);
    }

    public ProbeManager() {
        server = new Server(7000);
    }

    public ProbeManager addReceiver(Receiver receiver) {
        this.receivers.add(receiver);
        return this;
    }

    public void init() {
        receivers.forEach(r -> {
            System.out.println();
            server.addContext(r.getPath(), t -> {
                r.handle(new String(t.getRequestBody().readAllBytes(), "UTF-8"));
                t.sendResponseHeaders(200, 0);
                t.getResponseBody().write("".getBytes());
                t.getResponseBody().close();
            });
        });
        this.server.start();
    }
}