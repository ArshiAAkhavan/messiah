package model;

import model.Rule.Rule;

import java.util.ArrayList;

public class Probe {
    String listenPath;
    ArrayList<Rule>rules=new ArrayList<Rule>();
    model.Responce.Responce Responce;

    public Probe(String listenPath) {
        this.listenPath = listenPath;
    }

}
