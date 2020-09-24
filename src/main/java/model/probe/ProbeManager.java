package model.probe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

public class ProbeManager {

    private ArrayList<Probe> probes=new ArrayList<>();

    @JsonIgnore
    Server server;

    public ProbeManager(int port) {
        server =new Server(port);
    }
    public ProbeManager() {
        server= new Server(8000);
    }

    public void addProbe(Probe probe){
        this.probes.add(probe);
    }

    public void init(){
        probes.forEach(p -> server.addContext(p.getPath(), t -> {
            p.handle(new String(t.getRequestBody().readAllBytes(), "UTF-8"));
            t.sendResponseHeaders(200, 0);
            t.getResponseBody().write("".getBytes());
            t.getResponseBody().close();
        }));
        server.start();
    }
}
