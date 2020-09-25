package model.probe;

import model.rule.filter.Filter;
import org.junit.jupiter.api.Test;

public class ProbeTest {

    public Probe generateTestProbe(){
        Probe p=new Probe();
        p.addRule(new Filter("test-technology","in"));
        p.addRule(new Filter("hardware-problem","not-in"));
        return p;
    }

    @Test
    public void handle(){

    }

}
