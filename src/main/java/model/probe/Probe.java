package model.probe;

import model.rule.Rule;
import model.action.Action;

import java.util.ArrayList;

public class Probe {
    ArrayList<Rule>rules=new ArrayList<>();
    Action action;

    public Probe(){}


    public void handle(String input){
        System.err.println();
        int sum = this.rules.stream().mapToInt(r -> r.passes(input) ? 1 : 0).sum();
        if (sum==this.rules.size()){
            this.action.act(input);
        }
    }
    public void addRule(Rule rule){
        this.rules.add(rule);
    }

    public void setAction(Action action) {
        this.action = action;
    }



}
