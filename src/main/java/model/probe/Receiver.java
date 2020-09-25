package model.probe;

import java.util.ArrayList;

public class Receiver {
    private String path;
    ArrayList<Probe>probes=new ArrayList<>();

    public Receiver(String path) {
        this.path=path;
    }

    public Receiver handle(String input){
        this.probes.forEach(p -> p.handle(input));
        return this;
    }

    public Receiver addProbe(Probe probe){
        this.probes.add(probe);
        return this;
    }
    public String getPath() {
        return this.path;
    }
    public Receiver setPath(String path) {
        this.path=path;
        return this;
    }

}
