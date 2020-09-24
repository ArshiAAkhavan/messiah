package model;

import model.Rule.Rule;
import model.Responce.Action;

import java.util.ArrayList;
public class Probe {
    String listenPath;
    ArrayList<Rule>rules=new ArrayList<Rule>();
    Action Action;

    public Probe(String listenPath) {
        this.listenPath = listenPath;
    }

    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    public void setAction(Action action) {
        Action = action;
    }
}
