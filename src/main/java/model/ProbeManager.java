package model;

import java.io.OutputStream;
import java.util.ArrayList;

public class ProbeManager {
    private ArrayList<Probe> probes=new ArrayList<>();
    ServerListener serverListener;

    public void addProbe(Probe probe){
        this.probes.add(probe);
        serverListener=new ServerListener(8000);
    }

    public void init(){
        probes.forEach(p -> serverListener.addContext(p.getPath(), t -> {
            p.handle(new String(t.getRequestBody().readAllBytes(), "UTF-8"));
            t.sendResponseHeaders(200, 0);
            OutputStream os = t.getResponseBody();
            os.write("".getBytes());
            os.close();
        }));
        serverListener.start();
    }
}
