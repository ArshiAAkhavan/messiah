package model.probe;

import model.rule.Rule;
import model.responce.Action;

import java.util.ArrayList;

public class Probe {
    String listenPath;
    ArrayList<Rule>rules=new ArrayList<>();
    Action action;

    public Probe(String listenPath) {
        this.listenPath = listenPath;
    }

    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public void handle(String input){
        int sum = this.rules.stream().mapToInt(r -> r.passes(input) ? 1 : 0).sum();
        if (sum==this.rules.size()){
            this.action.act(input);
        }
    }

    public String getPath() {
        return this.listenPath;
    }
}
