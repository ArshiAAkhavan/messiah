package model.probe;

import java.util.ArrayList;

public class Receiver {
    private String path;
    ArrayList<Probe>probes=new ArrayList<>();

    public void handle(String input){
        this.probes.forEach(p -> p.handle(input));
    }

    public void addProbe(Probe probe){
        this.probes.add(probe);
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path=path;
    }

}
