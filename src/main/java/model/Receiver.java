package model;

import model.probe.Probe;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Receiver {
    private String path;
    private ArrayList<Probe>probes=new ArrayList<>();

    public void handle(String input){
        this.probes.forEach(p -> p.handle(input));
    }

    public String getPath() {
        return this.path;
    }
}
