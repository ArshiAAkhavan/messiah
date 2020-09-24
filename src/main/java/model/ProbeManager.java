package model;

import java.util.ArrayList;

public class ProbeManager {
    private ArrayList<Probe> probes=new ArrayList<Probe>();
    ServerListener serverListener;

    public void addProbe(Probe probe){
        this.probes.add(probe);
        serverListener=new ServerListener(8000);
    }

    public void init(){
        probes.forEach(p -> serverListener.addContext(p.getPath(), t -> p.handle(t.getRequestBody().toString())));
        serverListener.start();
    }
}
