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
        server = new Server(8000);
    }

    public void addReceiver(Receiver receiver) {
        this.receivers.add(receiver);
    }

    public void init() {
        for (Receiver r : receivers) {
            System.out.println();
            server.addContext(r.getPath(), t -> {
                r.probes.get(0).handle(new String(t.getRequestBody().readAllBytes(), "UTF-8"));
//                r.handle(new String(t.getRequestBody().readAllBytes(), "UTF-8"));
                t.sendResponseHeaders(200, 0);
                t.getResponseBody().write("".getBytes());
                t.getResponseBody().close();
            });
        }
    }
}